package com.citi.gfts.base.multiauth.sec.svc;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;

@Service
public class ContextService {
	
	@Autowired
	private HttpServletRequest request;
	
	
	public String getUser() { 
		return SecurityContextHolder.getContext().getAuthentication().getName(); 
	}
	
	public String getRoles() { 
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			roles.add(grantedAuthority.getAuthority()); 
		}
		return roles.toString();
	}
	
	public String getContextDetails() {
		//
		Map<String, String> ctx = new LinkedHashMap<String, String>(); 
		//
		ctx.put( "url", request.getRequestURI());
		ctx.put("sid", RequestContextHolder.currentRequestAttributes().getSessionId()); 
		ctx.put("user", SecurityContextHolder.getContext().getAuthentication().getName()); 
		//
		List<String> roles = new ArrayList<String>(); 
		for (GrantedAuthority grantedAuthority : SecurityContextHolder.getContext().getAuthentication().getAuthorities()) {
			roles.add(grantedAuthority.getAuthority()); 
		}
		ctx.put("roles", roles.toString()); 
		
		//
		return ctx.toString();

		
	}
	


}
