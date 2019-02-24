package com.handybudget.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;

import org.springframework.security.crypto.bcrypt.BCrypt;

public class PasswordUtil {

	public static String hashPassword(String password){
		
		return BCrypt.hashpw(password, BCrypt.gensalt());
	
	}

	public static boolean validatePassword(String passwordToCheck, String storedPassword) {
		
		return BCrypt.checkpw(passwordToCheck, storedPassword);
		
	}
	
	
}
