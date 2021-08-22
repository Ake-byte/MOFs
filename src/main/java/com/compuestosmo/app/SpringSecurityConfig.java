package com.compuestosmo.app;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.compuestosmo.app.auth.handler.LoginSuccesHandler;
import com.compuestosmo.app.models.service.JpaUserDetailsService;

@EnableGlobalMethodSecurity(securedEnabled=true, prePostEnabled=true)
@Configuration
public class SpringSecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Autowired
	private LoginSuccesHandler successHandler;
	
	
	@Autowired
	private BCryptPasswordEncoder passwordEncoder;
	
	@Autowired
	private JpaUserDetailsService userDetailsService;
	
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/", "/css/**", "/js/**", "/register/**", "/layout/layout/**", "/newPassword/**", "/newPasswordForm/**").permitAll()
		.antMatchers("/Clasificacion/listadoClasificacionMateriales/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/CompuestoMOF/listarMateriales/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/CompuestoMOF/fichaMaterial/{id}/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/Clasificacion/verClasificacion/{id}/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/uploads/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/expedienteMaterial/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/listarExpedientes/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/verSecciones/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/formExpediente/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/formPrueba/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/formSeccion/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/CompuestoMOF/formMaterial/**").hasAnyRole("ADMIN", "USER2")
		.antMatchers("/Clasificacion/formClasificacion/**").hasAnyRole("ADMIN")
		.antMatchers("/eliminar/**").hasAnyRole("ADMIN")
		.antMatchers("/PersonalAutorizado/listarRoles/**").hasAnyRole("ADMIN")
		.antMatchers("/PersonalAutorizado/formUsuario/**").hasAnyRole("ADMIN")
		.antMatchers("/PersonalAutorizado/verRol/**").hasAnyRole("ADMIN")
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
	}

}
