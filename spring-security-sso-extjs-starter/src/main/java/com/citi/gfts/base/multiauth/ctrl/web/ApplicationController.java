package com.citi.gfts.base.multiauth.ctrl.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.citi.gfts.base.multiauth.sec.svc.ContextService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/")
@Slf4j
public class ApplicationController {
	
	@Autowired
	ContextService contextService;

	@GetMapping({"", "/app", "/app/home"})
	public ModelAndView getHome() {
		log.info(contextService.getContextDetails());
		//
		ModelAndView mav = new ModelAndView("home");
		
		if ( contextService.getUser().equals("admin"))
			mav = new ModelAndView("admin");
		
		mav.addObject("user", contextService.getUser()); 
		mav.addObject("roles", contextService.getRoles().toLowerCase()); 
		return mav; 
	}
	
	@GetMapping({"/admin", "/admin/home"})
	public ModelAndView getAdmin() {
		log.info(contextService.getContextDetails());
		//
		ModelAndView mav = new ModelAndView("admin");
		mav.addObject("user", contextService.getUser()); 
		mav.addObject("roles", contextService.getRoles().toLowerCase()); 
		return mav; 
	}
	
	@RequestMapping({"/app/logout", "/logout"})
	public ModelAndView getLogout(HttpServletRequest request) throws ServletException {
		log.info(contextService.getContextDetails());
		return new ModelAndView("redirect:/local/logout");
	}

}