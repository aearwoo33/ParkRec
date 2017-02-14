package com.aearwood.coordinator.repositories;

import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.domain.Team;

public interface TeamRepository extends CrudRepository<Team, Integer>{
		public Set<Team> findAllByType(SportType type);
}

