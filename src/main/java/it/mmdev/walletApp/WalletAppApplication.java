package it.mmdev.walletApp;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@ComponentScan("it.mmdev.services")
@EnableJpaRepositories("it.mmdev.repos")
public class WalletAppApplication {

	public static void main(String[] args) {
		SpringApplication.run(WalletAppApplication.class, args);
	}

}
