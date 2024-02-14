package com.security.be.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

	String MAINPAGE = "MainPage.html";
	String FOODPAGE = "food.html";
	String MAPPAGE = "map.html";

	@GetMapping("/")
	public String goMain () {
		return MAINPAGE;
	}

	@GetMapping("/goFood")
	public String goFood () {
		return FOODPAGE;
	}

	@GetMapping("/goMap")
	public String goMap () {
		return MAPPAGE;
	}


}
