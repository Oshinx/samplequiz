package com.rewritingmole.models;

import java.util.ArrayList;
import java.util.List;

public class QuestionPaper {
   private String name;
   private String assessmentId;
   private String userId;
   private String courseId;
   private String status;
   private String direction;
   private String course;
   private String totalNumberOfQuestion;
   private String releaseDate;
   private String expiredDate;
   private String startTime;
   private String duration;
   private String randomization;
   private String feedback;
   private String instruction;
   private String questionPaperType;
   private String questionSize;
   private List<Question> listOfQuestion = new ArrayList<>();
   
   
			   
			 public String getQuestionPaperType() {
					return questionPaperType;
				}
				public void setQuestionPaperType(String questionPaperType) {
					this.questionPaperType = questionPaperType;
				}
				public String getQuestionSize() {
					return questionSize;
				}
				public void setQuestionSize(String questionSize) {
					this.questionSize = questionSize;
				}
			public List<Question> getListOfQuestion() {
					return listOfQuestion;
				}
				public void setListOfQuestion(List<Question> listOfQuestion) {
					this.listOfQuestion = listOfQuestion;
				}
			public String getInstruction() {
				return instruction;
			}
			public void setInstruction(String instruction) {
				this.instruction = instruction;
			}
		
			public String getName() {
					return name;
				}
		    public void setName(String name) {
					this.name = name;
				}
			public String getAssessmentId() {
				return assessmentId;
			}
			public void setAssessmentId(String assessmentId) {
				this.assessmentId = assessmentId;
			}
			public String getUserId() {
				return userId;
			}
			public void setUserId(String userId) {
				this.userId = userId;
			}
			public String getCourseId() {
				return courseId;
			}
			public void setCourseId(String courseId) {
				this.courseId = courseId;
			}
			public String getStatus() {
				return status;
			}
			public void setStatus(String status) {
				this.status = status;
			}
			public String getDirection() {
				return direction;
			}
			public void setDirection(String direction) {
				this.direction = direction;
			}
			public String getCourse() {
				return course;
			}
			public void setCourse(String course) {
				this.course = course;
			}
			public String getTotalNumberOfQuestion() {
				return totalNumberOfQuestion;
			}
			public void setTotalNumberOfQuestion(String totalNumberOfQuestion) {
				this.totalNumberOfQuestion = totalNumberOfQuestion;
			}
			public String getReleaseDate() {
				return releaseDate;
			}
			public void setReleaseDate(String releaseDate) {
				this.releaseDate = releaseDate;
			}
			public String getExpiredDate() {
				return expiredDate;
			}
			public void setExpiredDate(String expiredDate) {
				this.expiredDate = expiredDate;
			}
			public String getStartTime() {
				return startTime;
			}
			public void setStartTime(String startTime) {
				this.startTime = startTime;
			}
			public String getDuration() {
				return duration;
			}
			public void setDuration(String duration) {
				this.duration = duration;
			}
			public String getRandomization() {
				return randomization;
			}
			public void setRandomization(String randomization) {
				this.randomization = randomization;
			}
			public String getFeedback() {
				return feedback;
			}
			public void setFeedback(String feedback) {
				this.feedback = feedback;
			}
			   
   
   
}
