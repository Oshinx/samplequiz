package com.rewritingmole.model;

public class Course {
 private String courseId;
 private String courseName;
 private String direction;
 
 
 public Course () {
	 
 }
 
public Course(String courseId, String courseName, String direction) {
	this.courseId = courseId;
	this.courseName = courseName;
	this.direction = direction;
}


public String getCourseId() {
	return courseId;
}
public void setCourseId(String courseId) {
	this.courseId = courseId;
}
public String getCourseName() {
	return courseName;
}
public void setCourseName(String courseName) {
	this.courseName = courseName;
}
public String getDirection() {
	return direction;
}
public void setDirection(String direction) {
	this.direction = direction;
}
 
 
}
