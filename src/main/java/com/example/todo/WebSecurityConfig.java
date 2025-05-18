package com.example.todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.example.todo.service.login.UserService;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {
	@Autowired
	private UserService userService;
    @Bean
	public PasswordEncoder passwordEncoder(){
		return new BCryptPasswordEncoder();
	}

    @Bean
    public SecurityFilterChain SecurityFilterChain(HttpSecurity http) throws Exception{
    		http
			.authorizeHttpRequests((requests) -> requests
				.requestMatchers("/login","/register").permitAll()
				.anyRequest().authenticated()
			)
			.formLogin((form) -> form
				.loginPage("/login")
				.permitAll()
                .defaultSuccessUrl("/tasks",true)
                .failureUrl("/login")
			)
			.logout((logout) -> logout.permitAll())
			.rememberMe((remember) -> remember
				.key("aVeerySecurekey")
				.rememberMeParameter("remember-me")
				.tokenValiditySeconds(1800)
				.userDetailsService(userService)
			);
			

		return http.build();
    }
}
