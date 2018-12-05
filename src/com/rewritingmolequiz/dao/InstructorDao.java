package com.rewritingmolequiz.dao;

import java.io.IOException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.codec.DecoderException;
import org.apache.commons.codec.binary.Hex;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.rewritingmole.models.Course;
import com.rewritingmole.models.QuestionPaper;
import com.rewritingmole.models.QuestionPool;
import com.rewritingmole.models.Result;

public class InstructorDao {
	   
		Connection connection = null;
		DataConnector dataConnector;
		PreparedStatement preparedStatement;
       
		public InstructorDao() {
		     setConnection();
		}
		
	       public void setConnection() {
	    	   this.dataConnector = new DataConnector();
	    		this.connection = this.dataConnector.getConnected();
	    		
	       }
	      
	       
	       public QuestionPool getQuestionPool(String userId) throws JsonParseException, JsonMappingException, IOException {
		     	String sql = "SELECT assessment, HEX(assessment) from assessmentpool  where user_id=?";
		     	try {
		     		System.out.println("outside  the sql statement ");
		 			preparedStatement = connection.prepareStatement(sql);
		 			 preparedStatement.setString(1, userId); 
		 		        ResultSet rs = preparedStatement.executeQuery();
		 		        while(rs.next()) {
		 		          QuestionPool  pool = new QuestionPool();
		 		          ObjectMapper mapper = new ObjectMapper();
		 		            
		 	//	       pool = mapper.readValue(rs.getString(builder.toString()), QuestionPool.class);
		 		      
		 		        String s= rs.getString("HEX(assessment)");
		 		         // JsonGenerator  gen =f._createParser(s.getBytes(), "UTF-8");		 		         
		 		          byte t [] = Hex.decodeHex(s.toCharArray());
		 		          String value = new String(t, StandardCharsets.UTF_8);
		 		                  mapper.enableDefaultTyping();
		 		              QuestionPool p = mapper.readValue(value, QuestionPool.class);
		 		              for(int i = 0 ; i < p.getListOfQuestion().size(); i++) {
		 		            	  System.out.println(p.getListOfQuestion().get(i).getQuestionText());
		 		              }
		 		            
		 		        	return pool = mapper.readValue(value, QuestionPool.class) ;
		 		        } 
		     	}catch (SQLException | DecoderException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		        
		     	
		     	return null;
		     	

		   }
	       
	       public void createQuestionPool(String userId, String assessment) throws JsonParseException, JsonMappingException, IOException {
		     	String sql = "INSERT INTO assessmentpool(user_id,assessment) VALUES(?,?)";
		     	try {
		 			 preparedStatement = connection.prepareStatement(sql);
		 			 preparedStatement.setString(1, userId); 
		 			 preparedStatement.setString(2, assessment);
		 			 
		 			 preparedStatement.executeUpdate();
		 		       
		     	}catch (SQLException e) {
		 			// TODO Auto-generated catch block
		 			e.printStackTrace();
		 		}
		        
		  
		     	

		   }
	       
	       
	       public void updateQuestionPool(String userId,String question) throws SQLException {
	    	   String sql = "update assessmentpool set assessment=? where user_id=?";
	    	   preparedStatement = connection.prepareStatement(sql);
	    	   preparedStatement.setString(1, question);
	    	   preparedStatement.setString(2, userId);
	      
	    	   preparedStatement.executeUpdate();
                System.out.println("done");
	       }
	       
	       //retrieve the status of the question paper 
	       //lecturer ids
	       //assessement name
	       
	       public List<QuestionPaper>  getquestionPapersInfo(String userId){
	    	   String sql ="SELECT * from assessement_bank where user_id=?";
	    	  List<QuestionPaper> listOfQuestionPapers =  new ArrayList<>();
	    	   try {
	    	    preparedStatement = connection.prepareStatement(sql);  
	    	    preparedStatement.setString(1, userId);
				ResultSet resultSet = preparedStatement.executeQuery();
				
				while(resultSet.next()) {
					QuestionPaper paper = new QuestionPaper();
					paper.setAssessmentId(resultSet.getString("assessment_id"));	
					paper.setName(resultSet.getString("assessmentname"));
					paper.setUserId(resultSet.getString("user_id"));
					paper.setCourseId(resultSet.getString("course_id"));
					paper.setStatus(resultSet.getString("assessment_status"));
				// add assessment when serialization is complete	assessment
	                  listOfQuestionPapers.add(paper);	
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	    	   
	    	   return listOfQuestionPapers;
	       }
	       
	       
	       public void modifyQuestionPaperStatus(String assessmentId, String status) throws SQLException {
	    	   String sql = "UPDATE assessement_bank  SET assessment_status=? where assessment_id =?";
	    	   preparedStatement = connection.prepareStatement(sql);
	    	   preparedStatement.setString(1, status);
	    	   preparedStatement.setString(2, assessmentId);
	    	   preparedStatement.executeUpdate();
	    	 
	       }
	      
	       public void createAssessment(String assessment_id,String assessmentname, String user_id,String course_id,
	    		   String assessment, String assessment_status, String feedback ) {
	    	   String sql = "INSERT INTO assessement_bank (assessment_id,assessmentname,user_id,course_id,assessment,"
	    	   		+ "  assessment_status,assessment_feedback) VALUES(?,?,?,?,?,?,?) ";
	 			 try {
					preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, assessment_id); 
		 			preparedStatement.setString(2, assessmentname);
		 			preparedStatement.setString(3, user_id);
		 			preparedStatement.setString(4, course_id);
		 			preparedStatement.setString(5, assessment);
		 			preparedStatement.setString(6, assessment_status);
		 			preparedStatement.setString(7, feedback);
		 			preparedStatement.executeUpdate();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
	 			
	    	   
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
	       
	       public List<Result> getInstructorResults(String courseId){
		    	String sql = "SELECT course_id,userid,score FROM grade where course_id=?";
		    	List<Result> list = new ArrayList<>();
		    	 
		    	try {
		    		preparedStatement = connection.prepareStatement(sql);
					preparedStatement.setString(1, courseId);
					
					ResultSet rs = preparedStatement.executeQuery();
					while(rs.next()) {
					Result result = new Result();
					result.setCourseid(rs.getString("course_id"));
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
	     	
	       public List<Course> getInstructorsCourses(String userId) throws SQLException{
	   		String sql = "SELECT course_id FROM course_registration where user_id =?";
	   		List<Course> listOfCourses = new ArrayList<>();
	   		  preparedStatement = connection.prepareStatement(sql);
	   		  preparedStatement.setString(1,userId);
	          ResultSet rs = preparedStatement.executeQuery();
	           while(rs.next()) {
	           	Course course = new Course();
	           	course.setCourseId(rs.getString("course_id"));
	           	listOfCourses.add(course);
	           }  
	   		return listOfCourses;
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
		
	       
	       
}	       
	       
	       
	       
