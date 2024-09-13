package it.mmdev.utils;

import java.security.SecureRandom;
import java.util.Base64;

public class SaltUtility {
	
	public static String createSaltValue() {
		SecureRandom random = new SecureRandom();
		byte[] salt = new byte[16];
		random.nextBytes(salt);
		
		return Base64.getEncoder().encodeToString(salt);
	}

}
