package com.aearwood.coordinator.repositories;

import java.util.Date;
import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aearwood.coordinator.domain.GameSchedule;

public interface GameScheduleRepository extends CrudRepository<GameSchedule, Integer>{

	List<GameSchedule> findByDateTime(Date dateTime);
	}
