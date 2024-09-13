package it.mmdev.utils;

import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import it.mmdev.enums.ErrorCode;
import it.mmdev.exceptions.CustomLogicException;
import it.mmdev.models.TokenModel;

@Component
public class TokenUtility {
	
	@Value("jwt_secret_key")
	private String jwtSecretKey;

	@Value("jwt_token_short_lifetime")
	private Integer shortJwtTokenLifetime;
	
	@Value("jwt_token_lifetime")
	private Integer jwtTokenLifetime;
	
	public TokenModel createJwtToken(Long userId) {
		Date generationDate = new Date();
	    Date expirationDate = new Date(generationDate.getTime()
	    		+ (long) (jwtTokenLifetime * 1000));
	    
	    String token = Jwts.builder
	    		().setSubject(userId.toString())
	    		.setIssuedAt(generationDate)
	    		.setExpiration(expirationDate)
	    		.signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
	    	
	      return new TokenModel(token, expirationDate);
	}
	
	public TokenModel createShortJwtToken(Long userId) {
		Date generationDate = new Date();
	    Date expirationDate = new Date(generationDate.getTime()
	    		+ (long) (shortJwtTokenLifetime * 1000));
	    
	    String token = Jwts.builder
	    		().setSubject(userId.toString())
	    		.setIssuedAt(generationDate)
	    		.setExpiration(expirationDate)
	    		.signWith(SignatureAlgorithm.HS256, jwtSecretKey).compact();
	    	
	      return new TokenModel(token, expirationDate);
	}
	
	public boolean isTokenValid(String token) throws CustomLogicException {
		try {
			Jwts.parser().setSigningKey(this.jwtSecretKey).parseClaimsJws(token);
			return true;
		} catch (ExpiredJwtException e) {
			System.out.println("JWT Token is expired " +token);
			return false;
		} catch(JwtException e) {
			System.out.println("JWT Token is invalid " +token);
			return false;
		} catch(Exception e) {
			throw new CustomLogicException(ErrorCode.TOKEN_WRONG_FORMAT);
		}
	}
}
