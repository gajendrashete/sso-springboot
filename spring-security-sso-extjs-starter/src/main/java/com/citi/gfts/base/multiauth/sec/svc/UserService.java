package com.citi.gfts.base.multiauth.sec.svc;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.citi.gfts.base.multiauth.db.access.AppUserRepository;
import com.citi.gfts.base.multiauth.db.model.AppUser;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class UserService implements UserDetailsService {
	
	@Autowired
	AppUserRepository appUserRepository; 
	
	@Autowired 
	PasswordEncoder passwordEncoder; 

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		log.info("looking up username:" + username);
		User user = null; 
		
		List<AppUser> users = appUserRepository.findByUsername(username);
		
		//
		if ( users.size() > 0  )  { 
			AppUser appUser = users.get(0); 
			user = new User( 
					appUser.getUsername(), 
					appUser.getPassword(), 
					getAuthorities(Arrays.asList(appUser.getRoles().split(",")))
					); 
			log.info("returning user : " + user.getUsername() + " : " +  user.getAuthorities());
			return user; 
			
		} else { 
			log.info("No user in DB with : " + username);
			throw new UsernameNotFoundException(username); 
		}
		
		

	}
	
	private static List<GrantedAuthority> getAuthorities(List<String> roles) {
	     return  
	    		 roles
	    		 .stream()
	    		 .map(role -> new SimpleGrantedAuthority("ROLE_" + role))
	    		 .collect(Collectors.toList()); 
	} 
	
	/*

		String str = "one,two,three"; 
		
		     List<String> listStr  =  Stream.of(str.split(","))
		      .map (elem -> new String(elem))
		      .collect(Collectors.toList());
	
		     List<GrantedAuthority> authorities =  
		    		 roles
		    		 .stream()
		    		 .map(role -> new SimpleGrantedAuthority(role))
		    		 .collect(Collectors.toList()); 
		     	      
		

		
		return Stream.of(roles).forEach(role -> new SimpleGrantedAuthority("ROLE_" + role)).collect(Collectors.toList());
		

		String str = "one,two,three"; 
		     List<String> listStr  =  Stream.of(str.split(","))
		      .map (elem -> new String(elem))
		      .collect(Collectors.toList());
		}
		
		List<GrantedAuthority> authorities = new ArrayList<>();
		for (String role : roles) {
			authorities.add(new SimpleGrantedAuthority("ROLE_" + role));
		}
		return authorities;
		
	}
*/
	
}
