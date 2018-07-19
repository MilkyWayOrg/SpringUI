package com.milkyway.SpringUI.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView getHome(@RequestParam(required=false) String name) {
		ModelAndView model=new ModelAndView("index");
		model.addObject("name",name);
		RestTemplate restTemplate = new RestTemplate();
        String quote = restTemplate.getForObject("http://gturnquist-quoters.cfapps.io/api/random", String.class);
        log.info(quote.toString());
        model.addObject("msg",quote);        
		return model;
	}
}
