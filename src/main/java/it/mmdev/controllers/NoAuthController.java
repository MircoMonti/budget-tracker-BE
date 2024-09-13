package it.mmdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import it.mmdev.enums.ResponsesStatus;
import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.requests.ForgotPasswordRequest;
import it.mmdev.requests.LoginRequest;
import it.mmdev.requests.RegisterRequest;
import it.mmdev.responses.LoginResponse;
import it.mmdev.responses.SimpleResponse;
import it.mmdev.services.NoAuthService;
import lombok.extern.slf4j.Slf4j;

@RestController("no-auth")
@Slf4j
public class NoAuthController {
	
	@Autowired
	NoAuthService noAuthService;
	
	
	@PostMapping("/login")
	public LoginResponse login(@RequestBody LoginRequest request) throws Exception {
		log.info("Performng /login request...");
		
		try {
			
			LoginResponse response = noAuthService.login(request.getUsername(), request.getPassword());
			return response;
			
		} catch(Exception e) {
			log.error("Error performin /login request", e);
			throw e;
		}
	}
	
	@PostMapping("/register")
	public LoginResponse register(@RequestBody RegisterRequest request) throws CustomLogicException, Exception {
		log.info("Performing /register request...");
		
		try {
			
			LoginResponse response = noAuthService.register(request.getUsername(), 
					request.getEmail(),
					request.getPassword(), 
					request.getFirstname(),
					request.getLastname());
			return response;
			
		} catch(CustomLogicException e) {
			throw e;
		} catch(Exception e) {
			log.error("Error performing /register request", e);
			throw e;
		}
	}
	
	@PostMapping("/sendEmailResetPsw")
	public ResponseEntity<SimpleResponse> sendEmailResetPsw(@RequestBody ForgotPasswordRequest request) 
			throws CustomLogicException, Exception {
		
		log.info("Performing /sendEmailResetPsw request...");
		
		try {
			
			noAuthService.sendEmailResetPsw(request.getEmail());
			return ResponseEntity.ok(new SimpleResponse(ResponsesStatus.OK, "Sent email reset password"));
		
		} catch(CustomLogicException e) {
			throw e;
		} catch(Exception e) {
			log.error("Error performing /sendEmailResetPsw request...", e);
			throw e;
		}
		
	}

}
