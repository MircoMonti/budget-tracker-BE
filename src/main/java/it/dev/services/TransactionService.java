package it.dev.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TransactionService {
	
	@Autowired
	UserService userService;

	public void getTransactions() {
		
	}
	
	public void addTransaction(Long userId, Long categoryId, Float importo, String transactinType) {
		
		// check if user exists
		Optional<User> userExists = userService.getUserFromId(userId);
		
		// check if category exists and category is assigned to user
		
		// check if transactionType exists
		
		
		
		
	}
	
}
