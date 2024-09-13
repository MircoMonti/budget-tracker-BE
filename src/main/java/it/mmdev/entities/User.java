package it.mmdev.entities;

import java.util.Date;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.Temporal;
import jakarta.persistence.TemporalType;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "USER", schema = "wallet")
@Getter
@Setter
public class User {
	
	@Id
	@Column(name = "USER_ID", updatable = false, nullable = false)
	private Long userId; /* serve sequence */
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "PASSWORD")
	private String password;
	
	@Column(name = "SALT_VALUE")
	private String saltValue;
	
	@Column(name = "FIRST_NAME")
	private String firtName;
	
	@Column(name = "LAST_NAME")
	private String lastName;
	
	@Column(name = "EMAIL")
	private String email;
	
	@Column(name = "PROFILE_CODE")
	private String profileCode;

	@Column(name = "TOKEN")
	private String token;
	
	@Column(name = "TOKEN_EXPIRATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date tokenExpirationDate;
	
	@Column(name = "LAST_LOGIN_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date lastLoginDate;

	@Column(name = "LOGOUT_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date logoutDate;
	
	@Column(name = "PASSWORD_EXPIRATION_DATE")
	@Temporal(TemporalType.TIMESTAMP)
	private Date passwordExpirationDate;

}
