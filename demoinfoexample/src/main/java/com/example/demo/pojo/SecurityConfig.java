package com.example.demo.pojo;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig  {

	@Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeHttpRequests((authz) -> authz
                .requestMatchers("/swagger-ui.html**", "/v3/api-docs/**", "/swagger-ui/**", "/h2-console/**",
                		 "/api/rewards/calculate","/api/customers/register","/api/transactions/**").permitAll()
                .anyRequest().authenticated()
            )
            .formLogin().permitAll()
            .and()
            .logout().permitAll();

        // Disable CSRF and frame options for H2 console
        http.csrf().disable();
        http.headers().frameOptions().disable();

        return http.build();
    }
	
}