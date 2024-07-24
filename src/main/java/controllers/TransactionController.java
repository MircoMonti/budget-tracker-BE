package controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import it.dev.requests.AddTransactionRequest;

@RestController
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;

	@PostMapping("/addTransaction")
	public void addTransaction(AddTransactionRequest request) {
		
		System.out.println("Performing /addTransaction...");

		try {
			
			
			
		} catch(Exception e) {
			System.out.println("Error performing /addTransaction...");
			e.printStackTrace();
		}
		
		
	}
	
}
