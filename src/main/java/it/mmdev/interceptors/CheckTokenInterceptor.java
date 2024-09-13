package it.mmdev.interceptors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;

import com.fasterxml.jackson.databind.ObjectMapper;

import it.mmdev.enums.RequestParams;
import it.mmdev.exceptions.NotAuthorizedException;
import it.mmdev.exceptions.TokenExpiredException;
import it.mmdev.responses.RestCustomError;
import it.mmdev.utils.TokenUtility;
import it.mmdev.utils.ValidationErrorConverter;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class CheckTokenInterceptor implements HandlerInterceptor {
	
	@Autowired
	TokenUtility tokenService;
	
	private static final String APPLICATION_JSON = "application/json";
	
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object object) throws Exception {
		try {
			
			String token = request.getParameter(RequestParams.token.name());
			
			if (token == null || token.trim().equals("")) 
				throw new Exception("RequestParam " +RequestParams.token.name()+ " is mandatory");
			
			if (!tokenService.isTokenValid(token)) 
				throw new TokenExpiredException();
			
			
		} catch(TokenExpiredException | NotAuthorizedException e) { 
			RestCustomError error = ValidationErrorConverter.convertExceptionToRestCustomError(e);
			response.setContentType(APPLICATION_JSON);
			response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
			ObjectMapper objMapper = new ObjectMapper();
			response.getWriter().write(objMapper.writeValueAsString(error));
			return false;
		} catch(Exception e) {
			RestCustomError error = ValidationErrorConverter.convertExceptionToRestCustomError(e);
			response.setContentType(APPLICATION_JSON);
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
			ObjectMapper objMapper  = new ObjectMapper();
			response.getWriter().write(objMapper.writeValueAsString(error));
			return false;
		}
		
		return true;
	}
	
	
	
}
