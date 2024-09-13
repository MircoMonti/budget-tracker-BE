package it.mmdev.utils;

import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class PasswordUtility {
	
	public static String encodePassword(String clearPsw, String salt) throws Exception {
		if (clearPsw == null || clearPsw.isBlank() || salt == null || salt.isBlank()) 
			throw new Exception("");
		
		String saltedPsw = clearPsw + salt;
		BCryptPasswordEncoder pswEncoder = new BCryptPasswordEncoder();
		String hashedPsw = pswEncoder.encode(saltedPsw);
		
		return hashedPsw;
	}
	
	/* Returns true if both passwords match
	 * 
	 * 
	 * @param String clearPsw --> password sent by the client
	 * @param String userSaltValue --> salt value of user (taken from db)
	 * @param String dbUserPassword --> crypted psw of user (taken from db)
	 * */
	public static boolean checkPassword(String clearPsw, String dbUserPassword, String userSaltValue) {
		String saltedPassword = clearPsw + userSaltValue;
		BCryptPasswordEncoder encoderPassword = new BCryptPasswordEncoder();
		
		return encoderPassword.matches(saltedPassword, dbUserPassword);
	}
	
	public static boolean checkPasswordMatches(String newPsw, String oldPsw, String userSaltValue) {
		String saltedNewPsw = newPsw + userSaltValue;
		String  saltedOldPsw = oldPsw + userSaltValue;
		BCryptPasswordEncoder encoderPassword = new BCryptPasswordEncoder();
		
		return encoderPassword.matches(saltedNewPsw, saltedOldPsw);
	}
	
	


}
