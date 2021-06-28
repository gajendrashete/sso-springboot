package com.citi.gfts.base.multiauth.sec.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsByNameServiceWrapper;
import org.springframework.security.web.authentication.LoginUrlAuthenticationEntryPoint;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationProvider;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;
import org.springframework.security.web.authentication.preauth.RequestHeaderAuthenticationFilter;
import org.springframework.web.filter.GenericFilterBean;

import com.citi.gfts.base.multiauth.sec.svc.UserService;

@Configuration
@EnableWebSecurity
@Order(2)
public class SsoLoginSecurityConfig extends WebSecurityConfigurerAdapter {

	@Autowired
	private UserService userService;


	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http
			.csrf().disable()
			.addFilterAfter(siteMinderFilter(), RequestHeaderAuthenticationFilter.class)
			.authenticationProvider(preAuthProvider()).authorizeRequests()
			.antMatchers("/css/**", "/images/**", "/js/**", "/local/**").permitAll()
			.antMatchers("/api/**").hasAnyAuthority("ROLE_USER","ROLE_API")
			.antMatchers("/admin/**").hasAnyAuthority("ROLE_ADMIN")
			.antMatchers("/app/**").hasAnyAuthority("ROLE_USER","ROLE_API")
			.antMatchers("/**").hasAnyAuthority("ROLE_ADMIN","ROLE_USER","ROLE_API")
			.and()
			.authorizeRequests().anyRequest().authenticated()
		.and()
			.exceptionHandling()
			.authenticationEntryPoint(new LoginUrlAuthenticationEntryPoint("/local/login?sso"));

	}


	@Autowired
	@Override
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		super.configure(auth);
		auth.authenticationProvider(preAuthProvider());
		DaoAuthenticationProvider userDetailsBasedAuthenticationProvider = new DaoAuthenticationProvider();
		userDetailsBasedAuthenticationProvider.setUserDetailsService(userService);
		auth.authenticationProvider(userDetailsBasedAuthenticationProvider);
	}

	private AuthenticationProvider preAuthProvider() {
		PreAuthenticatedAuthenticationProvider preAuthProvider = new PreAuthenticatedAuthenticationProvider();
		
		preAuthProvider.setPreAuthenticatedUserDetailsService(new UserDetailsByNameServiceWrapper<PreAuthenticatedAuthenticationToken>(userService));
		return preAuthProvider;
	}

	//@Autowired
	protected GenericFilterBean siteMinderFilter() throws Exception {
		RequestHeaderAuthenticationFilter filter = new RequestHeaderAuthenticationFilter();
		
		// Default value is SM_USER for request header key, change it here if your SSO provider populates it differently
		// filter.setPrincipalRequestHeader("SM_USER"); 

		filter.setContinueFilterChainOnUnsuccessfulAuthentication(false);
		filter.setExceptionIfHeaderMissing(false);
		filter.setAuthenticationManager(authenticationManager());
		return filter;
	}

}