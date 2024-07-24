package it.dev.requests;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class AddTransactionRequest {
	
	private Long userId;
	private Long categoryId;
	private Long accountId;
	private Float importo;
		
	public AddTransactionRequest(Long userId, Long accountId, Long categoryId, Float importo) {
		this.userId = userId;
		this.accountId = accountId;
		this.categoryId = categoryId;
		this.importo = importo;
	}
	

}
