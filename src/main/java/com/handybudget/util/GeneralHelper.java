package com.handybudget.util;

public class GeneralHelper {

	public static boolean isEmptyString(String value) {
		
		if(value == null || value.trim().isEmpty()) {
			return true;
		}
		return false;
		
		
	}
	
}
