package it.dev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import exceptions.UserNotFoundExcepion;
import tables.User;

@Service
public class UserService {

	@Autowired
	UserRepository userRepo;
	
	public Optional<User> getUserFromId(Long userId) throws UserNotFoundExcepion {
		
		try {
			
		} catch(Exception e) {
			throw new UserNotFoundExcepion("User not found");
		}
		
	}
}

