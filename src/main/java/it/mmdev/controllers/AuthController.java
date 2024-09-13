package it.mmdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.mmdev.enums.ResponsesStatus;
import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.requests.ChangePasswordRequest;
import it.mmdev.requests.ResetPasswordRequest;
import it.mmdev.requests.TokenRequest;
import it.mmdev.responses.SimpleResponse;
import it.mmdev.services.UserService;
import lombok.extern.slf4j.Slf4j;

@RestController("auth")
@Slf4j
public class AuthController {
	
	@Autowired
	UserService userService;
	

	@PostMapping("/logout")
	public ResponseEntity<SimpleResponse> logout(Long userId) throws Exception {
		log.info("Performing /logout request...");
		
		try {
			
			if (userId == null) throw new Exception("User id cannot be null");
			
			userService.logout(userId);
			
			return ResponseEntity.ok(new SimpleResponse(ResponsesStatus.OK.name(), 
					"User logged out successfully"));
		
		} catch(Exception e) {
			log.error("Error performing /logout request", e);
			throw e;
		}
	}
	
	@PostMapping("/changePsw")
	public ResponseEntity<SimpleResponse> changePsw(TokenRequest tokenRequest, @RequestBody ChangePasswordRequest request) throws CustomLogicException, Exception {
		log.info("Performing /changePsw request...");
		
		try {
			
			if (request.getOldPassword() == null  || request.getOldPassword().isBlank()) 
				throw new Exception("Old password is mandatory");
			if (request.getNewPassword() == null || request.getNewPassword().isBlank()) 
				throw new Exception("New password is mandatory");
			
			userService.changePassword(tokenRequest.getToken(), 
					request.getOldPassword(), 
					request.getNewPassword());
			
			return ResponseEntity.ok(new SimpleResponse(ResponsesStatus.OK.name(), "Password changed successfully"));
			
		} catch(CustomLogicException e) {
			throw e;
		}
		catch(Exception e) {
			log.error("Error performing /changePsw request", e);
			throw e;
		}
	}
	
	@PostMapping("/resetPsw")
	public ResponseEntity<SimpleResponse> resetPsw(TokenRequest tokenRequest, @RequestBody ResetPasswordRequest request) throws CustomLogicException, Exception {
		log.info("Performing /resetPsw request...");
		
		try {
			
			userService.resetPassword(tokenRequest.getToken(), request.getNewPassword());
			return  ResponseEntity.ok(new SimpleResponse(ResponsesStatus.OK.name(), "Password reset was successfully"));
			
		} catch(CustomLogicException e) {
			throw e;
		}
		catch(Exception e) {
			log.error("Error performing /resetPsw request", e);
			throw e;
		}
	}
}
