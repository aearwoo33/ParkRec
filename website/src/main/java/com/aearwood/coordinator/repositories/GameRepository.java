package com.aearwood.coordinator.repositories;

import java.util.Date;
import java.util.List;
import java.util.Set;

import org.springframework.data.repository.CrudRepository;

import com.aearwood.coordinator.domain.Game;
import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.domain.Team;
import com.aearwood.coordinator.dto.GameDTO;

public interface GameRepository extends CrudRepository<Game, Integer>{

	Set<Game> findByDateTime(Date dateTime);
	public Set<Game> findAllByType(SportType type);
	}
