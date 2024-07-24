package tables;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name="UTENTE", schema="public")
public class User {

	@Id
	@Column(name = "ID_UTENTE")
	Long userId;
	
	@Column(name = "NOME")
	String name;
	
	@Column(name = "EMAIL")
	String email;
	
	@Column(name = "TOKEN")
	String token;
	
	@Column(name = "PASSWORD")
	String password;
	
	@Column(name = "SALT_VALUE")
	String saltValue;
	
	@Column(name = "FLAG_ATTIVO")
	boolean flag_active;
	
	
}
