package com.rewritingmolequiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rewritingmole.model.Course;

public class StudentDao {

	 
		Connection connection = null;
		DataConnector dataConnector;
		PreparedStatement preparedStatement;
		
		public StudentDao() {
			setConnection();
		}
		
	       public void setConnection() {
	    	   this.dataConnector = new DataConnector();
	    		this.connection = this.dataConnector.getConnected();
	    		
	       }
	       
	       public  void createStudent(String userid,String email,String password) throws SQLException {
	    	   String sql = "INSERT INTO user (user_id,email,password,role)VALUES(?,?,?,?)";
	           preparedStatement = connection.prepareStatement(sql);
	           preparedStatement.setString(1, userid);
	           preparedStatement.setString(2, email);
	           preparedStatement.setString(3, password);
	           preparedStatement.setString(4, "student");
	           preparedStatement.executeUpdate();
	       }
	       
	   	   public List<Course> getCourses() throws SQLException{
			String sql = "SELECT * FROM course";
			List<Course> listOfCourses = new ArrayList<>();
			preparedStatement = connection.prepareStatement(sql);
	        ResultSet rs = preparedStatement.executeQuery();
	        while(rs.next()) {
	        	Course course = new Course();
	        	course.setCourseId(rs.getString("course_id"));
	        	course.setCourseName(rs.getString("coursename"));
	        	course.setDirection(rs.getString("course_direction"));
	        	listOfCourses.add(course);
	        }  
			return listOfCourses;
		}
	   	   
	       
	   	 public void studentRegisterUser(String studentId, String courseId) {
	            //get course id
	    	
	    	  String sql = "INSERT INTO course_registration (user_id,course_id)VALUES(?,?)";
	          
	          try {
	        	  preparedStatement = connection.prepareStatement(sql);
	        	  preparedStatement.setString(1, studentId);
	              preparedStatement.setString(2,courseId);
				  preparedStatement.executeUpdate();
				  
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	      } 
	       
	     //check to see if student exist  and has registered for the course
	     public boolean studentExits(String email) {
	     	String sql = "SELECT * from user where email=? AND role=?";
	     	try {
	     		System.out.println("outside  the sql statement ");
	 			preparedStatement = connection.prepareStatement(sql);
	 			 preparedStatement.setString(1, email); 
	 			 preparedStatement.setString(2, "student");
	 		        ResultSet rs = preparedStatement.executeQuery();
	 		        while(rs.next()) {
	 		        
	 		        	return true;
	 		        } 
	     	}catch (SQLException e) {
	 			// TODO Auto-generated catch block
	 			e.printStackTrace();
	 		}
	        
	     	
	     	return false;
	     	

	   }
	   	public String getCourseId(String courseName) {
	   		 
     		String  sql = "SELECT course_id FROM course where coursename=?";
     		
     		try {
     			preparedStatement = connection.prepareStatement(sql);
     			preparedStatement.setString(1, courseName);
     			ResultSet rs = preparedStatement.executeQuery();
     			
     			   if(rs.next() == true) {
     			 String courseId = rs.getString("course_id");
     			
     			   return courseId;
     			   }
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
     		return null;
     	}
     	
		
}
