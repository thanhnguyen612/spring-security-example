package com.test.spring.securtity.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {
		@Bean
		public PasswordEncoder passwordEncoder() {
				return new BCryptPasswordEncoder();
		}

		@Bean
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
				http.csrf().disable()
						.authorizeHttpRequests((authorize) -> authorize
								.anyRequest()
								.authenticated())
						.httpBasic(Customizer.withDefaults());
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

				return http.build();
		}

		@Bean
		public UserDetailsService userDetailsService(){
				UserDetails user = User.builder()
						.username("test")
						.password(passwordEncoder().encode("password"))
						.roles("USER")
						.build();

				return new InMemoryUserDetailsManager(user);
		}
}
