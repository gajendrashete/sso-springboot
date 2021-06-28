package com.citi.gfts.base.multiauth.sec.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@Order(1)
public class FormLoginSecurityConfig extends WebSecurityConfigurerAdapter {

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.config.annotation.web.configuration.
	 * WebSecurityConfigurerAdapter#configure(org.springframework.security.
	 * config.annotation.web.builders.HttpSecurity)
	 */
	@Override
	protected void configure(HttpSecurity http) throws Exception {
		

		http
			.csrf().disable()
			.authorizeRequests()
			.antMatchers("/css/**", "/images/**", "/js/**", "/local/**").permitAll()
			.antMatchers("/api/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER","ROLE_API")
			.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/app/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER","ROLE_API")
			.antMatchers("/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER","ROLE_API")
			.and()
			.formLogin().loginPage("/local/login").permitAll().defaultSuccessUrl("/app/home", true)
			.and()
			.logout().logoutUrl("/local/logout").logoutSuccessUrl("/local/login?logout").permitAll();
;

	}

}