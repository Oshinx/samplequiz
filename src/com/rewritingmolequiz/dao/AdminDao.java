package com.rewritingmolequiz.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rewritingmole.model.Course;
import com.rewritingmole.model.User;





public class AdminDao {
    
	Connection connection = null;
	DataConnector dataConnector;
	PreparedStatement preparedStatement;
	
	public AdminDao() {
		setConnection();
	}
	
       public void setConnection() {
    	   this.dataConnector = new DataConnector();
    		this.connection = this.dataConnector.getConnected();
    		
       }
	
       public boolean checkIfCourseId(String courseId) {
  		 
   		String  sql = "SELECT coursename FROM course where course_id=?";
   		
   		try {
   			preparedStatement = connection.prepareStatement(sql);
   			preparedStatement.setString(1, courseId);
   			ResultSet rs = preparedStatement.executeQuery();
   			System.out.println(connection.isReadOnly());
   			   if(rs.next() == true) {
   				   return true;
   			   }
   		} catch (SQLException e) {
   			// TODO Auto-generated catch block
   			e.printStackTrace();
   		}
   		return false;
   	}
   	
       
       
	public boolean checkIfCourseExit(String courseName) {
		 
		String  sql = "SELECT coursename FROM course where coursename=?";
		
		try {
			preparedStatement = connection.prepareStatement(sql);
			preparedStatement.setString(1, courseName);
			ResultSet rs = preparedStatement.executeQuery();
			System.out.println(connection.isReadOnly());
			   if(rs.next() == true) {
				   return true;
			   }
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	
	
	public boolean createCourse(String courseId, String courseName, String direction) {
		String sql = "INSERT INTO course (course_id,coursename,course_direction)VALUES(?,?,?)";
         
          try {
        	  preparedStatement = connection.prepareStatement(sql);
        	  preparedStatement.setString(1, courseId);
              preparedStatement.setString(2,courseName);
			  preparedStatement.setString(3,direction);
			  preparedStatement.executeUpdate();
			  return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return false;
	}
	
	public boolean deleteCourse(String courseId) {
         String sql  = "DELETE FROM course where course_id = ?";
         try {
			preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, courseId);
	         preparedStatement.executeUpdate();
	         return true;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
        return false;
	}
	
	
	public int getTotalStudent() throws SQLException {
		String sql = "SELECT role FROM user WHERE role=?";
        int total = 0;
        preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "student");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	total+=1;
        }
        
		return total;
	}
	
	public int getTotalInstructors() throws SQLException {
		String sql = "SELECT role FROM user WHERE role=?";
		int total = 0;
		preparedStatement = connection.prepareStatement(sql);
        preparedStatement.setString(1, "instructor");
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	total+=1;
        }  
		return total;
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
	
	public int getTotalCourses() throws SQLException {
		String sql = "SELECT course_id FROM course";
		int total = 0;
		preparedStatement = connection.prepareStatement(sql);
        ResultSet rs = preparedStatement.executeQuery();
        while(rs.next()) {
        	total+=1;
        }  
		return total;
	}
	
	
	
    public List<User> getInstructors(){
    	String sql = "SELECT role FROM user WHERE role=?";
		int total = 0;
		List <User> instructorList = new ArrayList<>();
		try {
			preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, "instructor");
		        ResultSet rs = preparedStatement.executeQuery();
		        while(rs.next()) {
		        	User  user = new User();
		        	user.setUserid(rs.getString("user_id"));
		        	user.setEmail(rs.getString("email"));
		        	user.setRole(rs.getString(" role"));
		        	instructorList.add(user);
		        }  
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	return instructorList;
	}
    
    //check to see if student exist  and has registered for the course
    public boolean studentExits(String studentId) {
    	String sql = "SELECT * from user where user_id=? AND role=?";
    	try {
    		System.out.println("outside  the sql statement ");
			preparedStatement = connection.prepareStatement(sql);
			 preparedStatement.setString(1, studentId); 
			 preparedStatement.setString(2, "student");
		        ResultSet rs = preparedStatement.executeQuery();
		        while(rs.next()) {
		        	System.out.println(rs.getString("user_id"));
		        	return true;
		        } 
    	}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
       
    	
    	return false;
    	

  }
    // check if the student has registered for the course
      public boolean isregistered(String studentId, String courseId ) {
    	  //
    	  String sql = "SELECT user_id from course_registration where user_id=? AND course_id=? "; 
    	  try {
  			preparedStatement = connection.prepareStatement(sql);
  			preparedStatement.setString(1, studentId);
  			preparedStatement.setString(2, courseId);
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
     	
      
      
      public void adminRegisterUser(String studentId, String courseId) {
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
      
      
}