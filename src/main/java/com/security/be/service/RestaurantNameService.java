package com.security.be.service;

import com.security.be.entity.RestaurantDate;
import com.security.be.entity.RestaurantName;
import com.security.be.repository.RestaurantNameRepository;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

@Service
@Log4j2
public class RestaurantNameService {
	private final RestaurantNameRepository restaurantNameRepository;
	private final RestaurantDateService restaurantDateService;

	public RestaurantNameService (RestaurantNameRepository restaurantNameRepository, RestaurantDateService restaurantDateService) {
		this.restaurantNameRepository = restaurantNameRepository;
		this.restaurantDateService = restaurantDateService;
	}

	public RestaurantName randomItem(){
		List<RestaurantName> restaurants = restaurantNameRepository.findAll();
		RestaurantName restaurantName = restaurants.get(new Random().nextInt(restaurants.size()));
		RestaurantDate restaurantDate = restaurantDateService.currentInfo ();
		if (restaurantDate.getName ().equals (restaurantName.getName ())) {
			restaurantNameRepository.updateCountByName (restaurantName.getName (),restaurantName.getCount ()+1);
			return restaurantName;
		} else {
			restaurantNameRepository.updateCountByName (restaurantDate.getName (), restaurantNameRepository.findByName (restaurantDate.getName ()).getCount () -1);
			restaurantDateService.updateToday (restaurantName.getName ());
			return restaurantNameRepository.findByName (restaurantName.getName ());
		}
	}

	public List<RestaurantName>count(){
		return restaurantNameRepository.findAll ();
	}

	public String addItem(String inp){
		if(inp.isEmpty ()){
			return "Name is Null";
		}
		RestaurantName restaurantName = new RestaurantName ();
		restaurantName.setName (inp);
		restaurantNameRepository.save (restaurantName);
		return "Success";
	}

	public String deleteItem(String inp){
		if(inp.isEmpty ()){
			return "Name is Null";
		}
		restaurantNameRepository.deleteByName(inp);
		return "Success";
	}

}
