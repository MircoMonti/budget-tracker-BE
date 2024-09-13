package it.mmdev.walletApp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import it.mmdev.interceptors.CheckTokenInterceptor;

public class WalletAppWebConfig {
	
	private String[] allowedOrigins;
	
	@Autowired
	CheckTokenInterceptor tokenInterceptor;
	
	@Bean
	public WebMvcConfigurer walletApp_webMvcConfigurer() {
		return new WebMvcConfigurer() {
		
			@Override
			public void addCorsMappings(CorsRegistry registry) {
				System.out.println("Cors allowed origins: " + String.join(",", allowedOrigins));
				registry.addMapping("/**").allowedOrigins(allowedOrigins);
			}
			
			@Override
			public void addInterceptors(InterceptorRegistry registry) {
				registry.addInterceptor(tokenInterceptor)
					.addPathPatterns("/transaction/**");
			}
		
		};
	}

}
