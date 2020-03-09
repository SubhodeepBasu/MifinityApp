package com.subho.mifinityapplication.security;

import java.io.IOException;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

/**
 * @author subasu This class is used to implement security in the spring
 *         application
 * @version 1.0
 */
@Configuration
@EnableWebSecurity
public class ApplicationSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserDetailsService userDetailsService;

	@Value("${admin.role}")
	private String adminRole;

	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests().antMatchers("/resources/**", "/userRegistration").permitAll()
				// .antMatchers("/userFunctions", "/createNewCard",
				// "/createNewCard").access("hasRole('USER')")
				.antMatchers("/admin").access("hasRole('ADMIN')").anyRequest().authenticated().and().formLogin()
				.successHandler(new AuthenticationSuccessHandler() {

					@Override
					public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
							Authentication authentication) throws IOException, ServletException {
						Set<String> roles = AuthorityUtils.authorityListToSet(authentication.getAuthorities());

						if (roles.contains(adminRole)) {
							response.sendRedirect("/searchExistingCard");
						} else {
							response.sendRedirect("/userCardFunctions");
						}

					}
				})

				.loginPage("/login").permitAll().and().logout().permitAll();
	}

	@Bean
	public AuthenticationManager customAuthenticationManager() throws Exception {
		return authenticationManager();
	}

	@Autowired
	public void configureGlobal(AuthenticationManagerBuilder auth) throws Exception {
		auth.userDetailsService(userDetailsService).passwordEncoder(bCryptPasswordEncoder());
	}

}
