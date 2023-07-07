package com.example.spring.securtity.config;

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
								.antMatchers("/admin/**").hasRole(Role.ADMIN.name())
								.antMatchers("/user/**").hasAnyRole(Role.ADMIN.name(), Role.USER.name())
								.anyRequest()
								.authenticated())
						.httpBasic(Customizer.withDefaults());
				http.sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

				return http.build();
		}

		@Bean
		public UserDetailsService userDetailsService() {
				UserDetails admin = User.builder()
						.username("admin")
						.password(passwordEncoder().encode("admin"))
						.roles(Role.ADMIN.name())
						.build();

				UserDetails user = User.builder()
						.username("user")
						.password(passwordEncoder().encode("user"))
						.roles(Role.USER.name())
						.build();
				return new InMemoryUserDetailsManager(admin, user);
		}
}
