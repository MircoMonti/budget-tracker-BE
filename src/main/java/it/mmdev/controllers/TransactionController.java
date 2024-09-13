package it.mmdev.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.requests.AddEditTransactionRequest;
import it.mmdev.requests.TokenRequest;
import it.mmdev.services.TransactionService;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping(value = "/transaction")
@Slf4j
public class TransactionController {
	
	@Autowired
	TransactionService transactionService;
	
	@PostMapping("/saveTransaction")
	public void saveTransaction(TokenRequest token, @RequestBody AddEditTransactionRequest request) throws CustomLogicException, Exception {
		log.info("Performing /saveTransaction request..");
		
		try {
			transactionService.saveTransaction();
		} catch(CustomLogicException e) {
			throw e;
		}
		catch(Exception e) {
			log.error("Error performing /saveTransaction request", e);
			throw e;
		}
	}
	
	@PostMapping("/editTransaction")
	public void editTransaction(TokenRequest token, @RequestBody AddEditTransactionRequest request) {
		log.info("Performing /editTransaction request..");
	}

}
