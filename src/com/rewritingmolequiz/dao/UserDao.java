package com.rewritingmolequiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.mindrot.jbcrypt.BCrypt;

import com.rewritingmole.model.User;

public class UserDao {
	Connection connection = null;
	DataConnector dataConnector;
	PreparedStatement preparedStatement;
	
	public  UserDao () {
		setConnection();
	}
       public void setConnection() {
    	   this.dataConnector = new DataConnector();
    		this.connection = this.dataConnector.getConnected();
    		
       }
      
       public User getUser(String username, String password)  {
    	   String sql = "SELECT * FROM user where email=?";
    	   try {
    	    preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, username);
		
	  
	    	   ResultSet rs = preparedStatement.executeQuery();
	    	   while(rs.next()) {
	    		   User user = new User();
	    		   user.setEmail(rs.getString("email"));
	    		   user.setUserid(rs.getString("user_id"));
	    		   user.setPassword(rs.getString("password"));
	    		   user.setRole(rs.getString("role"));
	    		   System.out.println(user.getPassword());
	    		   if(BCrypt.checkpw(password, user.getPassword())) {
	    			  
	    		     return user;
	    		   
	    		   }
	    	   }
	    	   
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	   
    	   return null;
       }
       
       
       public void updatePassword(String userEmail,String newPassword) {
    	   //
    	   
    	   try {
    		  
    		   String sql = "update user set password= ? where email=?";  
    		   
			   preparedStatement = connection.prepareStatement(sql);
			   preparedStatement.setString(1, BCrypt.hashpw(newPassword, BCrypt.gensalt()));
			   preparedStatement.setString(2, userEmail);
			   preparedStatement.executeUpdate();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       }
     
       public boolean createInstrutor(String userId, String email, String password) {
   		String sql = "INSERT INTO user (user_id,email,password,role)VALUES(?,?,?,?)";
            String pass = BCrypt.hashpw(password, BCrypt.gensalt());
             try {
           	  preparedStatement = connection.prepareStatement(sql);
           	  preparedStatement.setString(1, userId);
              preparedStatement.setString(2,email);
   			  preparedStatement.setString(3,pass);
   			  preparedStatement.setString(4,"instructor");
   			  preparedStatement.executeUpdate();
   			  return true;
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return false;
   	}
	
	
}
