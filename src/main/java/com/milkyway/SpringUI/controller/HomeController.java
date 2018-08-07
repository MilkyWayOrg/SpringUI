package com.milkyway.SpringUI.controller;


import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import com.fasterxml.jackson.core.JsonFactory;
import com.fasterxml.jackson.core.JsonParser;

@Controller
public class HomeController {

	private static final Logger log = LoggerFactory.getLogger(HomeController.class);

	@RequestMapping(value="/",method=RequestMethod.GET)
	public ModelAndView getHome() {
		ModelAndView model=new ModelAndView("index");
		return model;
	}
	
	@RequestMapping(value="/",method=RequestMethod.POST)
	public ModelAndView postHome(@RequestParam String name) {
		ModelAndView model=new ModelAndView("home");
		model.addObject("name",name);
		
		//Consuming REST API
		RestTemplate restTemplate = new RestTemplate();
		String quote = restTemplate.getForObject("http://localhost:8787/greeting/"+name, String.class);
		log.info(quote.toString());
		JSONObject obj=new JSONObject(quote);
		model.addObject("msg",obj.get("content"));
		return model;
	}
}
