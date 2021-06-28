package com.citi.gfts.base.multiauth.ctrl.web;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import com.citi.gfts.base.multiauth.sec.svc.ContextService;

import lombok.extern.slf4j.Slf4j;

@Controller
@RequestMapping("/local") 
@Slf4j
public class FormloginController {

	@Autowired
	ContextService contextService;
	
	@Value( "${application.security.sso}" )
	private Boolean sso;

	@RequestMapping("/login")
	public ModelAndView getLogin() {
		if(sso) { 
			log.info("*" + contextService.getContextDetails());
			return new ModelAndView("ssoonly");
		} else {
			log.info("*" + contextService.getContextDetails());
			return new ModelAndView("login");
		}
	}

	@RequestMapping("/logout")
	public ModelAndView getLogout(HttpServletRequest request) throws ServletException {

		
		if(sso) { 
			log.info("*" + contextService.getContextDetails());
			return new ModelAndView("ssoonly");
		} else {
			log.info("*" + contextService.getContextDetails());
			log.info("User will be logged out and redirected back to login page");
			request.logout();
			return new ModelAndView("login");
		}
		
	}

	@RequestMapping("/info")
	public ModelAndView getInfo() {
		log.info("*" + contextService.getContextDetails());
		return new ModelAndView("login");
	}

}