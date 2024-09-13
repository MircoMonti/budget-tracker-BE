package it.mmdev.services;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import it.mmdev.entities.User;
import it.mmdev.enums.ErrorCode;
import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.repos.UserRepo;
import it.mmdev.utils.PasswordUtility;
import it.mmdev.utils.SaltUtility;
import jakarta.transaction.Transactional;

@Service
public class UserService {
	
	@Autowired
	UserRepo userRepo;
	
	@Transactional
	public void logout(Long userId) throws CustomLogicException {
		Optional<User> user = userRepo.findById(userId);
		if (user.isEmpty())
			throw new CustomLogicException(ErrorCode.USER_NOT_FOUND);
		
		user.get().setLogoutDate(new Date());
		user.get().setToken(null);
		userRepo.save(user.get());
	}
	
	@Transactional
	public void changePassword(String token, String oldPsw, String newPsw) throws CustomLogicException, Exception {
		
		/* check if user exists */
		Optional<User> user = userRepo.findByToken(token);
		if(user.isEmpty())
			throw new CustomLogicException(ErrorCode.USER_NOT_FOUND);
		
		/* check if old and new passwords are the same */
		boolean pswMatch = PasswordUtility.checkPasswordMatches(newPsw, oldPsw, user.get().getSaltValue());
		if (pswMatch)
			throw new CustomLogicException(ErrorCode.PASSWORD_CANNOT_BE_SAME);
		
		/* set crypted password */
		String hashedPsw = PasswordUtility.encodePassword(newPsw, user.get().getSaltValue()); // crypting psw
		user.get().setPassword(hashedPsw);
		userRepo.save(user.get());
	}
	
	@Transactional
	public void resetPassword(String token, String newPsw) throws CustomLogicException, Exception {
		
		/* check if user exists */
		Optional<User> user = userRepo.findByToken(token);
		if(user.isEmpty())
			throw new CustomLogicException(ErrorCode.USER_NOT_FOUND);

		/* set crypted password */
		String salt = SaltUtility.createSaltValue();
		String hashedPsw = PasswordUtility.encodePassword(newPsw, salt);
		
		user.get().setSaltValue(salt);
		user.get().setPassword(hashedPsw);
		userRepo.save(user.get());
	}
}
