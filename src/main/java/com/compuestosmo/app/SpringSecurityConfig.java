package com.compuestosmo.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	
	
	//@Override
	//protected void configure(HttpSecurity http) throws Exception {
	//	http.authorizeRequests().antMatchers("/", "/css/**", "*/js/**", "/images/**", "/listar").permitAll()
	//	.antMatchers("/ver/**")
	//}

	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
		
	}
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		PasswordEncoder encoder = passwordEncoder();
		//UserBuilder users = User.withDefaultPasswordEncoder();
		//Codificar Contraseña
		UserBuilder users = User.builder().passwordEncoder(encoder::encode);
	
		//Crear Usuarios en memoria
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER"))
		.withUser(users.username("alan").password("12345").roles("USER"));
	
		
		
	}

}
