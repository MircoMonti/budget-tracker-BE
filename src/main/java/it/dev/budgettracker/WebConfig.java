package it.dev.budgettracker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Configuration
@Slf4j
public class WebConfig {
	
	@Value("${cors.allowed.origins}")
	private String[] corsAllowedOrigins;
	
	@Bean
	public SecurityFilterChain configure(HttpSecurity http) throws Exception {
		http.cors().and().csrf().disable();
		
        http.authorizeHttpRequests((requests) -> requests
                .requestMatchers("/**").permitAll().anyRequest().authenticated()).httpBasic();
    
        return http.build();
	  }
	
	@Bean
	public WebMvcConfigurer webConfigurer() {
		return new WebMvcConfigurer() {
			
			@Override
			public void addCorsMappings(CorsRegistry corsRegistry) {
				// fare per tutti i percorsi
				log.info("Cors allowed origins " + String.join(", ", corsAllowedOrigins));
				corsRegistry.addMapping("/percorso.../").allowedOrigins(corsAllowedOrigins);
			}
			
//			@Override
//			public void addInterceptors(InterceptorRegistry interceptorRegistry) {

			/* Interceptors da fare:
			 * 
			 * 	1. PER CONRTROLLO TOKEN E ESISTENZA UTENTE
			 * 	2. PER CONTROLLO PERMESSI UTENTE ?? 
			 * 
			 * */
			
			// questo da fare per ogni possibile interceptor esistente
//				interceptorRegistry.addInterceptor(/*interceptor*/)
//					.addPathPatterns("vari percorsi");
//			}
		};
	}

}
