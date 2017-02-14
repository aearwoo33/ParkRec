package com.aearwood.coordinator.repositories;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import com.aearwood.coordinator.domain.RefereeSchedule;

public interface RefereeScheduleRepository extends CrudRepository<RefereeSchedule, Integer>{
	List<RefereeSchedule> findByRefereeId( Integer refereeId);
}
