package com.security.be.repository;

import com.security.be.entity.RestaurantName;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

public interface RestaurantNameRepository extends JpaRepository<RestaurantName, Long> {
	void deleteByName (String inp);

	RestaurantName findByName(String inp);

	@Modifying
	@Transactional
	@Query("UPDATE RestaurantName n set n.count=:count where n.name=:name")
	void updateCountByName(@Param ("name")String name, @Param ("count")int count);
}
