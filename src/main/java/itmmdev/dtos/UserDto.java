package itmmdev.dtos;


import java.util.Date;

import it.mmdev.entities.User;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {
	
	private Long userId;
	private String username;
	private String firtName;
	private String lastName;
	private String email;
	private String profileCode;
	private Date passwordExpirationDate;
	// lista di permessi utente
	
	public UserDto(User user) {
		this.userId = user.getUserId();
		this.username = user.getUsername();
		this.firtName = user.getFirtName();
		this.lastName = user.getLastName();
		this.email = user.getEmail();
		this.profileCode = user.getProfileCode();
		this.passwordExpirationDate = user.getPasswordExpirationDate();
	}

}
