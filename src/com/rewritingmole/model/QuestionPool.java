package com.rewritingmole.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionPool {
private List<Question> listOfQuestion = new ArrayList<>();

public List<Question> getListOfQuestion() {
	return listOfQuestion;
}

public void setListOfQuestion(List<Question> listOfQuestion) {
	this.listOfQuestion = listOfQuestion;
}

}
