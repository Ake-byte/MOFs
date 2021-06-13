package com.compuestosmo.app;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.User.UserBuilder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.compuestosmo.app.auth.handler.LoginSuccesHandler;
import com.compuestosmo.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	@Autowired
	private DataSource dataSource;
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/register/**").permitAll()
		.antMatchers("/listadoClasificacionMateriales/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/listarMateriales/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/fichaMaterial/{id}/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/verClasificacion/{id}/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/uploads/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/formMaterial/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/formPrueba/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/formClasificacion/**").hasAnyRole("ADMIN")
		.antMatchers("/formDirector/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/listadoUsuarios/**").hasAnyRole("ADMIN")
		.antMatchers("/listadoDirectores/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/index/**").hasAnyRole("ADMIN", "USER2", "USER1")
		.anyRequest().authenticated()
		.and()
		.formLogin()
			.successHandler(successHandler)
			.loginPage("/login")
			.usernameParameter("email")
		.permitAll()
		.and()
		.logout().permitAll()
		.and()
		.exceptionHandling().accessDeniedPage("/error_403");
	}

	
	
	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder builder) throws Exception{
		
		builder.userDetailsService(userDetailsService)
		.passwordEncoder(passwordEncoder);
		//.usersByUsernameQuery("select email, pwd, enabled from users where email=?")
		//.authoritiesByUsernameQuery("select u.email, a.authority from authorities a inner join users u on (a.user_id=u.users_id) where u.email=?");
		/*
	
		//Crear Usuarios en memoria
		
		//-----------------admin
		//Administrador: tiene permiso a todas las funciones del sistema
		
		//-----------------user2
		//Investigador2: tiene permiso a todas las funciones referentes a los MOFs
		
		//-----------------user1
		//Investigador1: no tiene permiso a nada, sin embargo, está registrado en el sistema
		
		builder.inMemoryAuthentication()
		.withUser(users.username("admin").password("12345").roles("ADMIN", "USER2"))
		.withUser(users.username("user2").password("12345").roles("USER2"))
		.withUser(users.username("user1").password("12345").roles("USER1"));
	*/
	}

}
