package com.rewritingmole.instructor.controllerz;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;

import org.json.CDL;
import org.json.JSONArray;
import org.json.JSONObject;

import com.rewritingmole.models.Result;
import com.rewritingmolequiz.utilz.CSVUtils;

/**
 * Servlet implementation class DownloadResult
 */
@WebServlet("/DownloadResult")
public class DownloadResult extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String filename = "result.csv";
        
        HttpSession session = request.getSession();
        response.setContentType("text/csv");
        response.setHeader("Content-Disposition", "attachment; filename=\"result.csv\"");
        List<Result> list = (List) session.getAttribute("courseresult");
    	FileWriter fileWriter = new FileWriter("result.csv");
        OutputStream outputStream = response.getOutputStream();
    	StringBuilder sb = new StringBuilder();
    	sb.append("studentId");
    	sb.append(",");
    	sb.append("Score\n");
        for(Result r : list) {
        	sb.append(r.getUserid());
        	sb.append(",");
        	sb.append(r.getScore());
        	sb.append(",\n");
        //	 out.write(CSVUtils.writeLine(out, list2));
        }
        outputStream.write(sb.toString().getBytes());
        outputStream.flush();
        outputStream.close();
        response.sendRedirect("CourseGrade.jsp");
        }
        
    	String e = " <button type=\"submit\" class=\"btn btn-default btn-block pure-button pure-button-primary\">Submit</button>";
	}


	

