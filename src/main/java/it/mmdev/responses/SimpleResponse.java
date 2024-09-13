package it.mmdev.responses;

import it.mmdev.enums.ResponsesStatus;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SimpleResponse {
	
	private String code;
	private String message;
	
	public SimpleResponse(String code, String message) {
		this.code = code;
		this.message = message;
	}
	
	public SimpleResponse(ResponsesStatus code, String message) {
		this.code = code.name();
		this.message = message;
	}

}
