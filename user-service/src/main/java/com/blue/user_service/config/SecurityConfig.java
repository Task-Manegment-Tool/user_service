package com.blue.user_service.config;

import com.blue.user_service.security.JwtAuthenticationEntryPoint;
import com.blue.user_service.security.JwtAuthenticationFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;



@Configuration
public class SecurityConfig {
	
	
	
	@Autowired
	private JwtAuthenticationEntryPoint point;

	@Autowired	
	private JwtAuthenticationFilter filter;

	@Bean
	public SecurityFilterChain securiyFilterChain(HttpSecurity http) throws Exception {

		http.csrf(csrf -> csrf.disable())
				.cors(cors -> cors.disable())
				.authorizeHttpRequests(auth -> auth.requestMatchers("/private/users/assign_role/**").hasRole("ADMIN")
													.requestMatchers("/private/**").authenticated()
//													.requestMatchers("/private/getuser/**").permitAll()
													.requestMatchers("/public/**").permitAll()
													.anyRequest().authenticated())
				.exceptionHandling(ex -> ex.authenticationEntryPoint(point))
				.sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS))
				.logout(logout->logout.permitAll());

		http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

		return http.build();
	}

	@Bean
	public AuthenticationManager authenticationManager(AuthenticationConfiguration builder) throws Exception
	{
		return builder.getAuthenticationManager();
	}

}
