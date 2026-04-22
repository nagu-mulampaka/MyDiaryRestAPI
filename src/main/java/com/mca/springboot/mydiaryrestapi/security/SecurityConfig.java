package com.mca.springboot.mydiaryrestapi.security;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.provisioning.UserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
	
	@Bean
	public UserDetailsManager configureDataSource(DataSource dataSource) {
		UserDetailsManager userDetailsManager = new JdbcUserDetailsManager(dataSource);
		return userDetailsManager;
	}
	
	@Bean
	public SecurityFilterChain authorizeHttpRequests(HttpSecurity httpSecurity) throws Exception  {
	    httpSecurity
	    .authorizeHttpRequests((authorize) -> {    
	        authorize
	        .requestMatchers(HttpMethod.DELETE, "/v1.0/entries/**").hasRole("ADMIN")
	        .requestMatchers(HttpMethod.DELETE, "/v1.0/users/**").hasRole("ADMIN")
            .requestMatchers(HttpMethod.PUT, "/v1.0/entries/**").hasRole("MANAGER")
            .requestMatchers(HttpMethod.PUT, "/v1.0/users/**").hasRole("MANAGER")
            .requestMatchers(HttpMethod.POST, "/v1.0/entries/**").hasRole("MANAGER")
            .requestMatchers(HttpMethod.POST, "/v1.0/users/**").hasRole("MANAGER")
            .anyRequest().authenticated();  
	    })
	    .httpBasic((C) -> {})
	    .csrf((csrf) -> csrf.disable());

	    return httpSecurity.build();
	}
	
	// In Memory User Details ( username, password, roles )
	/* 
	@Bean
	public InMemoryUserDetailsManager configureInMemoryUsers() {
		UserDetails user1 = User.builder()
				            .username("nagu")
				            .password("{noop}nagu123")
				            .roles("ADMIN", "MANAGER")
				            .build();
		UserDetails user2 = User.builder()
				            .username("Hemanth")
				            .password("{noop}hemanth123")
				            .roles("EMPLOYEE", "MANAGER")
				            .build();
		UserDetails user3 = User.builder()
				            .username("Shiva")
				            .password("{noop}shiva123")
				            .roles("EMPLOYEE")
				            .build();
		InMemoryUserDetailsManager inMemoryUserDetails = new InMemoryUserDetailsManager(user1, user2, user3);
		return inMemoryUserDetails;
	}
	*/

}
