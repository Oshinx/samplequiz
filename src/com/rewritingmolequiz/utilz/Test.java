package com.rewritingmolequiz.utilz;

import java.io.File;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Clock;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.StringTokenizer;

import javax.mail.PasswordAuthentication;
import javax.swing.text.DateFormatter;

import org.json.CDL;
import org.json.JSONArray;

import com.rewritingmolequiz.dao.InstructorDao;

import sun.util.resources.cldr.ar.CalendarData_ar_LB;



public class Test {
    public static void main(String [] args) throws ParseException {
    	//Mailer.sendMail("DDSSSSSSS", "");
    	/*int option [] = {3,4,4,2,2};
    	        int qcount = 1;
    		
        for(int i = 0 ; i < 5; i++) {
        	int count = 0;
        	System.out.println("test "+i  );
        	for(int j = 0; j < option[i]; j++) {
        		count += 1;
        		System.out.println("option "+ count);

        	}
        System.out.println("========================");
        	qcount += 1;
        }
        
        String panelEnd ="<div class=\"form-group\">\n" + 
        		"                       <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">point</label>\n" + 
        		"                         <div class=\"col-sm-10\">\n" + 
        		"                           <input type=\"number\" min=\"0\" max=\"10\" class=\"form-control col-sm-10\" id=\"exampleInputName2\" required=\"required\">\n" + 
        		"                        </div>\n" + 
        		"                     </div>\n" + 
        		"\n" + 
        		"                     <div class=\"form-group\">\n" + 
        		"                      <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Feedback </label>\n" + 
        		"                        <div class=\"col-sm-10\">\n" + 
        		"                           <input type=\"text\" class=\"form-control col-sm-10\" id=\"exampleInputName2\" required=\"required\">\n" + 
        		"                        </div>\n" + 
        		"                     </div>\n" + 
        		"\n" + 
        		"                     <div class=\"form-group\">\n" + 
        		"                       <label for=\"inputEmail3\" class=\"col-sm-2 control-label\">Negative Point </label>\n" + 
        		"                         <div class=\"col-sm-10\">\n" + 
        		"                           <input type=\"number\" class=\"form-control\" max=\"10\" col-sm-10\"=\"\" min=\"0\" id=\"exampleInputName2\" required=\"required\">\n" + 
        		"                         </div>\n" + 
        		"                    </div>\n" + 
        		"                     <input type=\"hidden\" id=\"custId\" name=\"question\" value=\"\">\n" + 
        		"                  </div>\n" + 
        		"              </div>\n" + 
        		"            </div>\n" + 
        		"          </div>\n" + 
        		"\n" + 
        		"               </form>\n" + 
        		"";*/
    	/*
    	 InstructorDao  dao = new InstructorDao();
 	    List<QuestionPaper> questionPapers =   dao.getquestionPapersInfo();
 	     for(QuestionPaper paper: questionPapers) {
 	    	System.out.println(paper.getAssessmentId()); 
 	    	System.out.println(paper.getName());
 	    	System.out.println(paper.getUserId());
 	    	System.out.println(paper.getCourseId());
 	    	System.out.println(paper.getStatus());
 	    	
    	    	
    	     }*/
    	
    	String link = "./ManageAssessment?path=dashboard:868638682681:";
    	
    	StringTokenizer stringTokenizer = new StringTokenizer(link,":");
    	
    	String link2 = stringTokenizer.nextToken();
    	String id = stringTokenizer.nextToken();
    	System.out.println(link2 +"\n" +id);
 
    	String s ="\n" + 
    			"                     <div class=\"panel panel-default \">\n" + 
    			"                       <div class=\"panel-heading main-color-bg \">\n" + 
    			"                      <h3 class=\"panel-title main-color-bg\">Question 1</h3>\n" + 
    			"                    </div>\n" + 
    			"                          <table class=\"table\"> \n" + 
    			"                                \n" + 
    			"                                      <tbody> <tr>\n" + 
    			"                                        <th ><input type=\"checkbox\" name=\"d\" value=\"d\"></th> \n" + 
    			"                                          </tr>\n" + 
    			"                                        \n" + 
    			"                                        <tr>\n" + 
    			"                                        \n" + 
    			"                                         <td>Question 1</td> \n" + 
    			"                                         <td>Question Texvxxxxxxxxxxxxxxxxxxxxxxxx  xxxxxxxxxxxxxxxx xxxxCVCXVxxxxxxxxxxxxxxxx xxxxxxxxxxxxxxxxxxxx xxxxxxxxxxxxxxxxxxxxxxx  hhvvvvvvvvvvvvvvvvvvvvvvvvvvv vvvvvvvvvvvvvvvvvvvvvvvvvvvvt</td>\n" + 
    			"                                          </tr>\n" + 
    			"\n" + 
    			"                                          <tr>\n" + 
    			"                                         <td>A.</td> \n" + 
    			"                                         <td> Option Text</td>\n" + 
    			"                                          </tr>\n" + 
    			"\n" + 
    			"                                              \n" + 
    			"                                              </tbody>\n" + 
    			"\n" + 
    			"                                               </table>\n" + 
    			"                                                  <input type=\"hidden\" name=\"\" value=\"\" id=\"Course\">   \n" + 
    			"                                        \n" + 
    			"                          </div>";
    	
    	
    	
    	//2018-09-30
    	//2018-09-29
SimpleDateFormat  dateFormat = new SimpleDateFormat("yyyy-MM-dd");
Date date1 = dateFormat.parse("2018-09-30"); // release date
Date date2 = dateFormat.parse("2018-09-29"); // expired date

if(date1.compareTo(date2) > 0) {
	System.out.println("Date 1 is after Date 2");
}

if(date1.compareTo(date2) < 0) {
	System.out.println("Date 1 is before Date 2 ");		
}
//Calendar cal = ;

System.out.println();


System.out.println(Calendar.getInstance().getTime());


// get date 
// check if (release date == present date || present date < expired date)
//if()
LocalTime time = LocalTime.parse("12:30");


LocalTime.of(LocalTime.now().getHour(), LocalTime.now().getMinute());
//System.out.println((time.compareTo(presentTime)));
   
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
  }
}
