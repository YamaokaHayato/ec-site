package com.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityCustomizer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

	@Bean
	//staticの中のファイルを使用できるようにする記述
	public WebSecurityCustomizer webSecurityCustomizer() {
		return (web) -> web.ignoring().requestMatchers("/css/**", "/img/**", "/js/**", "/fonts/**");
	}

	@Bean
	public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
		//全てのパスを通すことができる記述
		http.authorizeHttpRequests().requestMatchers("/**").permitAll().anyRequest().authenticated();
		return http.build();
	}

	@Bean
	//パスワードのハッシュ化
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
