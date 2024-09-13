package it.mmdev.responses;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class RestCustomError {
	
	private String code;
	private String message;	

}
