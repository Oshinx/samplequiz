package com.rewritingmolequiz.dao;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rewritingmole.model.QuestionPool;

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
		     	String sql = "SELECT * from assessmentpool  where user_id=?";
		     	try {
		     		System.out.println("outside  the sql statement ");
		 			preparedStatement = connection.prepareStatement(sql);
		 			 preparedStatement.setString(1, userId); 
		 		        ResultSet rs = preparedStatement.executeQuery();
		 		        while(rs.next()) {
		 		          QuestionPool  pool = new QuestionPool();
		 		          ObjectMapper mapper = new ObjectMapper();
		 		       pool =  mapper.readValue(rs.getString("assessment"), QuestionPool.class);
		 		       
		 		        	return pool ;
		 		        } 
		     	}catch (SQLException e) {
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
	       
	       
	       public void updateQuestionPool(String question,String userId) throws SQLException {
	    	   String sql = "update assessmentpool set assessment=? where user_id=?";
	    	   preparedStatement = connection.prepareStatement(sql);
	    	   preparedStatement.setString(1, question);
	    	   preparedStatement.setString(2, userId);
	    	   preparedStatement.executeUpdate();
           
	       }
	       
	       
}	       
	       
	       
	       
