package it.mmdev.requests;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class AddEditTransactionRequest {
	
	private Long transactionId; // se presente sono in edit
	
	private Long categoryId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date transactionDate;

}
