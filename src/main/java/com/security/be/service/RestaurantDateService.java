package com.security.be.service;

import com.security.be.entity.RestaurantDate;
import com.security.be.repository.RestaurantDateRepository;
import lombok.extern.log4j.Log4j;
import lombok.extern.log4j.Log4j2;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
@Log4j2
public class RestaurantDateService {
	private final RestaurantDateRepository restaurantDateRepository;

	public RestaurantDateService (RestaurantDateRepository restaurantDateRepository) {
		this.restaurantDateRepository = restaurantDateRepository;
	}

	public List<RestaurantDate> countTable () {
		return restaurantDateRepository.findAll (Sort.by (Sort.Direction.DESC, "date"));
	}

	public RestaurantDate currentInfo(){
		return restaurantDateRepository.findByDate (LocalDate.now ());
	}

	public int countNowDate (String inp) {
		return restaurantDateRepository.countRestaurantDatesByName (inp);
	}

	public boolean updateToday (String inp) {
		LocalDate currentDate = LocalDate.now ();

		RestaurantDate restaurantDate = restaurantDateRepository.findByDate (currentDate);

		if (restaurantDate != null) {
			restaurantDateRepository.updateNameByDate (inp,currentDate);
			return true;
		} else {
			RestaurantDate newRestaurantDate = new RestaurantDate ();
			newRestaurantDate.setName (inp);
			newRestaurantDate.setDate (currentDate);
			restaurantDateRepository.save (newRestaurantDate);
			return false;
		}
	}
}
