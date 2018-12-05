package com.rewritingmolequiz.dao;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewritingmole.models.Course;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmole.models.QuestionPool;
import com.rewritingmole.models.Result;

public class StudentDao {

	 
		Connection connection = null;
		DataConnector dataConnector;
		PreparedStatement preparedStatement;
		ResultSet rs;
		
		public StudentDao() {
			setConnection();
		}
		
	       public Connection setConnection() {
	    	   this.dataConnector = new DataConnector();
	    		this.connection = this.dataConnector.getConnected();
	    		return this.connection;
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
	   		 this.connection  = setConnection();
	    	
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
	     	 this.connection  = setConnection();
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
     		 this.connection  = setConnection();
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
	   	
		public String getCourseName(String courseId) {
	   		 
     		String  sql = "SELECT coursename FROM course where course_id=?";
     		 this.connection  = setConnection();
     		try {
     			preparedStatement = connection.prepareStatement(sql);
     			preparedStatement.setString(1, courseId);
     			ResultSet rs = preparedStatement.executeQuery();
     			
     			   if(rs.next() == true) {
     			 String courseName = rs.getString("coursename");
     			
     			   return courseName;
     			   }
     		} catch (SQLException e) {
     			// TODO Auto-generated catch block
     			e.printStackTrace();
     		}
	
     		return null;
     	}
     	
	   	public List<String>  getAllStudent() {
	   		List<String>  li = new ArrayList<>();
	   		String sql = "SELECT role from user where role like ?";
	   		String values  = "instructor";
	   	    this.connection  = setConnection();
	   		try {
				preparedStatement =connection.prepareStatement(sql);
				preparedStatement.setString(1, "%" +values +"%");
		   		ResultSet rs = preparedStatement.executeQuery();
		   		while(rs.next()) {
		   			String s = rs.getString("role");
		   			li.add(s);
		   		}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	
	   		return li;
	   	}
		
	   	
	   	private static String createQuery(int lenght) {
	   		String query = "select assessment,  HEX(assessment), assessment_status from assessement_bank where course_id  in (";
	   		StringBuilder queryBuilder = new StringBuilder(query);
	   		
	   		for(int i = 0; i < lenght; i++) {
	   			queryBuilder.append("?");
	   			if(i != lenght -1) {
	   				queryBuilder.append(",");
	   			}
	   		}
	   		queryBuilder.append(")");
	   		return queryBuilder.toString();
	   	}
	   	
	   	public List<String> printData() throws SQLException {
	   		List<String>  li = new ArrayList<>();
	   		String values [] = {"instructor"}; 
	   		String query = createQuery(values.length);
	   		System.out.println(query);
	   		
	   		preparedStatement = connection.prepareStatement(query);
	   	     int count = 0;
	       	for(int i =0; i< values.length; i++) {
	       		count +=1;
	       		preparedStatement.setString(count, values[i]); 		
	       	}
	       	ResultSet rs = preparedStatement.executeQuery();
	       	
	       	while(rs.next()) {
	       		String s = rs.getString("role");
	       		li.add(s);
	       	}
	   		return li;
	   	}
	   	
	   	
	   	public List<Course> getStudentRegisteredCourse(String userId){
	   		String sql = "SELECT course_id from course_registration where  user_id=?";
	   		List<Course> listOfCourse = new ArrayList<>();
	   	 this.connection  = setConnection();
	   		try {
	   			
				preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userId);
				 rs = preparedStatement.executeQuery();
				while(rs.next()) {
					Course c = new Course();
					c.setCourseId(rs.getString("course_id"));
					listOfCourse.add(c);
					
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	   		return listOfCourse; 
	   	}
	   	
	   	public List<QuestionPaper> getListOfQuestionPaper(String [] questionPaperCourseid) throws DecoderException, JsonParseException, JsonMappingException, IOException{
	    String sql = createQuery(questionPaperCourseid.length);
	    List<QuestionPaper>  list =  new ArrayList<>();
	    this.connection  = setConnection();
        try {
			preparedStatement  =connection.prepareStatement(sql);
			for(int i = 1; i <= questionPaperCourseid.length; i++) {
				preparedStatement.setString(1, questionPaperCourseid[i-1]);
			}
			ResultSet rs = preparedStatement.executeQuery();
			
	          while(rs.next()) {  
	        	   ObjectMapper mapper = new ObjectMapper();
 	//	       pool = mapper.readValue(rs.getString(builder.toString()), QuestionPool.class);
 		        String s= rs.getString("HEX(assessment)");
 		        String status = rs.getString("assessment_status");
 		         // JsonGenerator  gen =f._createParser(s.getBytes(), "UTF-8");		 		         
 		          byte t [] = Hex.decodeHex(s.toCharArray());
 		          String value = new String(t, StandardCharsets.UTF_8);
 		              QuestionPaper p = mapper.readValue(value, QuestionPaper.class);
 		              p.setStatus(status);
 		              list.add(p);
 		              for(int i =0 ; i < p.getListOfQuestion().size(); i++) {
 		            	  System.out.println(p.getListOfQuestion().get(i).getQuestionText());
	             
 		              }
        
        }} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
   
	   		
	   		
	   		
	  return list; 	}
	   	
	    public void storeResult(String courseid,String userid,String assessmentid, int score,String assessment) {
	    	  String sql = "INSERT INTO grade (course_id,userid,assessmentid,score,assessment)VALUES(?,?,?,?,?)";
	    	  this.connection  = setConnection();
	    	  try {
			      preparedStatement = connection.prepareStatement(sql);
				  preparedStatement.setString(1, courseid);
		    	  preparedStatement.setString(2, userid);
		    	  preparedStatement.setString(3, assessmentid);
		    	  preparedStatement.setInt(4, score);
		    	  preparedStatement.setString(5, assessment);
		    	  preparedStatement.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		   	
	    }
	    
	    public List<Result> getStudentResults(String userid){
	    	String sql = "SELECT course_id,userid,score FROM grade where userid=?";
	    	List<Result> list = new ArrayList<>();
	    
	    	try {
	    		preparedStatement = connection.prepareStatement(sql);
				preparedStatement.setString(1, userid);
				
				ResultSet rs = preparedStatement.executeQuery();
				while(rs.next()) {
				Result result = new Result();
				System.out.println(rs.getString("course_id")+"================== course id");
				System.out.println(getCourseName(rs.getString("course_id") )  + "================ course Name ");
				result.setCourseid(getCourseName(rs.getString("course_id")));
				result.setUserid(rs.getString("userid"));
				result.setScore(String.valueOf(rs.getInt("score")));
				list.add(result);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	  
	    	return list;
	    }
	    	
}
