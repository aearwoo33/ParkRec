package com.aearwood.coordinator.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aearwood.coordinator.domain.Athlete;

public interface AthleteRepository extends CrudRepository<Athlete, Integer>{
	List<Athlete> findByUserId(Integer userId);
}
