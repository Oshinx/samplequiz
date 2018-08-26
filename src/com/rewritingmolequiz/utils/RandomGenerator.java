package com.rewritingmolequiz.utils;

import java.util.Random;

public class RandomGenerator {
	public static String generateUniqueId() {
		long uniqueId = System.currentTimeMillis();
		String uniqueIdAsString = "ACK" + String.valueOf(uniqueId);
		return uniqueIdAsString;
	}
	
	public static String generateAssessmentId() {
		long uniqueId = System.currentTimeMillis();
		String uniqueIdAsString = "ASS" + String.valueOf(uniqueId);
		return uniqueIdAsString;
	}
	
	public static String generateQuestionId() {
		long uniqueId = System.currentTimeMillis();
		String uniqueIdAsString = "Que" + String.valueOf(uniqueId);
		return uniqueIdAsString;
	}
	
	public static String generatePlaceHolder() {
	String charcater = "abcdefghijklmnopqrstuvwxy";
	String randomPlaceHolder = "";
	int length = 5;
	char text [] = new char[length];
	Random random = new Random();
	char [] temp = new char[length];
	 
	for(int i = 0; i < length; i++) {
		text[i] = charcater.charAt(random.nextInt(charcater.length()));
	}
	
	for(int i = 0; i < text.length; i++) {
		randomPlaceHolder += text[i];
	}
	  return randomPlaceHolder;
	}
	
	public static String generatePassword() {
		String charcater = "abcdefghijklmnopqrstuvwxy";
		String randomPlaceHolder = "";
		int length = 8;
		char text [] = new char[length];
		Random random = new Random();
		char [] temp = new char[length];
		 
		for(int i = 0; i < length; i++) {
			text[i] = charcater.charAt(random.nextInt(charcater.length()));
		}
		
		for(int i = 0; i < text.length; i++) {
			randomPlaceHolder += text[i];
		}
		  return randomPlaceHolder;
		}
	
	public static String generateRandomCode() {
		String charcater = "0123456789";
		String randomPlaceHolder = "";
		int length = 6;
		char text [] = new char[length];
		Random random = new Random();
		char [] temp = new char[length];
		 
		for(int i = 0; i < length; i++) {
			text[i] = charcater.charAt(random.nextInt(charcater.length()));
		}
		
		for(int i = 0; i < text.length; i++) {
			randomPlaceHolder += text[i];
		}
		  return randomPlaceHolder;
		}
	
}
