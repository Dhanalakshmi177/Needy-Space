package com.helperorg.needyspace;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class HomeController {

	@RequestMapping(method = RequestMethod.GET,value="/home")
	public ModelAndView home() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("index");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/register")
	public ModelAndView register() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("register");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/needy")
	public ModelAndView needy() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("needy");
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.GET,value="/helper")
	public ModelAndView helper() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("helper");
		return modelAndView;
	}

}
