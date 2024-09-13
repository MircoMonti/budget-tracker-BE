package it.mmdev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mmdev.entities.User;
import it.mmdev.enums.ErrorCode;
import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.models.TokenModel;
import it.mmdev.repos.UserRepo;
import it.mmdev.responses.LoginResponse;
import it.mmdev.utils.EmailUtility;
import it.mmdev.utils.PasswordUtility;
import it.mmdev.utils.SaltUtility;
import it.mmdev.utils.TokenUtility;
import itmmdev.dtos.UserDto;
import jakarta.transaction.Transactional;

@Service
public class NoAuthService {
	
	@Autowired
	TokenUtility tokenService;
	
	@Autowired
	EmailUtility emailUtility;
	
	@Autowired
	UserRepo userRepo;

	@Transactional
	public LoginResponse login(String username, String password) throws CustomLogicException {
		
		// check if user exists
		Optional<User> userExists = userRepo.findByUsername(username);
		if (userExists.isEmpty())
			throw new CustomLogicException(ErrorCode.USER_NOT_FOUND);
		
		// check password
		String saltValue = userExists.get().getSaltValue();
		boolean passwordsMatch = PasswordUtility.checkPassword(password, userExists.get().getPassword(), saltValue);
		if (!passwordsMatch) 
			throw new CustomLogicException(ErrorCode.WRONG_PASSWORD);
		
		TokenModel token = tokenService.createJwtToken(userExists.get().getUserId());
		
		userExists.get().setToken(token.getToken());
		userExists.get().setTokenExpirationDate(token.getTokenExpirationDate());
		
		LoginResponse response = new LoginResponse(new UserDto(userExists.get()), token.getToken());		
		return response;
		
	}
	
	@Transactional
	public LoginResponse register(String username, String email, String password, 
			String firstname, String lastname) throws CustomLogicException, Exception {
		
		Optional<User> usernameExists = userRepo.findByUsername(username);
		if (usernameExists.isPresent())
			throw new CustomLogicException(ErrorCode.USERNAME_ALREADY_EXISTS);
		
		Optional<User> emailExists = userRepo.findByUsername(email);
		if (emailExists.isPresent())
			throw new CustomLogicException(ErrorCode.USER_EMAIL_ALREADY_EXISTS);
		
		/* creazione utente + crypt psw */

		
		/* set crypted password */
		String salt = SaltUtility.createSaltValue();
		String hashedPsw = PasswordUtility.encodePassword(password, salt);
		
		
		User newUser = new User();
		newUser.setFirtName(firstname);
		newUser.setLastName(lastname);
		newUser.setEmail(email);
		newUser.setUsername(username);
		newUser.setPassword(hashedPsw);
//		newUser.setProfileCode(hashedPsw);
		newUser.setSaltValue(salt);
		
		userRepo.saveAndFlush(newUser);
		
		/* creazione token */
		Optional<User> thisUser = userRepo.findByUsername(username);
		TokenModel tokenModel = tokenService.createJwtToken(thisUser.get().getUserId());
		
		newUser.setToken(tokenModel.getToken());
		newUser.setTokenExpirationDate(tokenModel.getTokenExpirationDate());
		userRepo.save(newUser);
		
		/*
		 * TODO: Mandare email di benvenuto con codice di verifica OTP ??
		 * */
		
		UserDto userDto = new UserDto(thisUser.get());
		LoginResponse loginRes = new LoginResponse(userDto, tokenModel.getToken());
		
		return loginRes;		
	}
	
	@Transactional
	public void sendEmailResetPsw(String email) throws CustomLogicException, Exception {
		
		/* controllo esistenza email */
		Optional<User> user = userRepo.getByEmail(email);
		if (user.isEmpty())
			throw new CustomLogicException(ErrorCode.USER_NOT_FOUND);
		
		/* leggere parametri db */		
		String body = "";
		String subject = "";
		
		/* creazione token temporaneao di 10 min */
		TokenModel tempToken = tokenService.createJwtToken(user.get().getUserId());
		user.get().setToken(tempToken.getToken());
		
		emailUtility.sendEmail(email, subject, body);
	}
	
}
