package it.mmdev.controllers;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("user-management")
public class UserController {
	
	// cambio psw
	
	
	// reset psw
	@PostMapping("/resetPsw")
	public void resetPsw() {
		System.out.println("Performing /sendEmailResetPsw request...");
		
		
	}
		
	// edit user (modifica di nome, cognome, username)

}
