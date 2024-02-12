package com.security.be.controller;

import com.security.be.entity.RestaurantDate;
import com.security.be.entity.RestaurantName;
import com.security.be.service.RestaurantDateService;
import com.security.be.service.RestaurantNameService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/restaurant")
public class RouletteController {

	private final RestaurantNameService restaurantNameService;
	private final RestaurantDateService restaurantDateService;

	private final String FOODPAGE = "food.html";

	public RouletteController (RestaurantNameService restaurantNameService, RestaurantDateService restaurantDateService) {
		this.restaurantNameService = restaurantNameService;
		this.restaurantDateService = restaurantDateService;
	}

	@GetMapping("/")
	public String countRestaurant(Model model){
		List<RestaurantName> list = restaurantNameService.count ();
		List<RestaurantDate> list2 = restaurantDateService.countTable ();
		model.addAttribute ("names", list);
		model.addAttribute ("dates",list2);
		return FOODPAGE;
	}

	@GetMapping("/random")
	@ResponseBody
	public String getRandomItem () {
		RestaurantName randomItem = restaurantNameService.randomItem ();

		return randomItem.getName ();
	}

	@PostMapping("/addRestaurant")
	@ResponseBody
	@Transactional
	public String addRestaurant (@RequestBody String inputText) {
		return restaurantNameService.addItem (inputText);
	}

	@PostMapping("/deleteRestaurant")
	@ResponseBody
	@Transactional
	public String deleteRestaurant (@RequestBody String inputText) {
		return restaurantNameService.deleteItem (inputText);
	}

}



