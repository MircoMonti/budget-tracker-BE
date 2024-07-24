package it.dev.budgettracker;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;

@SpringBootApplication
@ComponentScan({"services"})
@EntityScan({"tables"})
@EnableWebSecurity
public class BudgetTrackerApplication {

	public static void main(String[] args) {
		SpringApplication.run(BudgetTrackerApplication.class, args);
	}

}
