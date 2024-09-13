package it.mmdev.repos;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import it.mmdev.entities.User;

public interface UserRepo extends JpaRepository<User, Long> {
	
	@Query("SELECT u FROM User u WHERE u.username = :username")
	Optional<User> findByUsername(String username);
	
	@Query("SELECT u FROM User u WHERE u.email = :email")
	Optional<User> getByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE u.token = :token")
	Optional<User> findByToken(String token);

}
