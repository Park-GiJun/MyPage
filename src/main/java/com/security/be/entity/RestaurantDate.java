package com.security.be.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Entity
@Getter
@Setter
public class RestaurantDate {
	@Id
	private Long id;

	private String name;
	private LocalDate date;


}
