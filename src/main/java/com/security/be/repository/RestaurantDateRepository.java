package com.security.be.repository;

import com.security.be.entity.RestaurantDate;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface RestaurantDateRepository extends JpaRepository<RestaurantDate, Long> {

	@Query("SELECT count(r) FROM RestaurantDate r WHERE r.name = :name")
	int countRestaurantDatesByName(@Param("name") String name);

	RestaurantDate findByDate(LocalDate date);

	@Modifying
	@Transactional
	@Query("UPDATE RestaurantDate r SET r.name = :name WHERE r.date = :date")
	void updateNameByDate(@Param("name") String name, @Param("date") LocalDate date);

}
