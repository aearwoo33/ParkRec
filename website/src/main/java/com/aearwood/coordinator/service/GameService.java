package com.aearwood.coordinator.service;

import java.util.List;
import java.util.Set;

import org.springframework.stereotype.Service;
import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.dto.GameDTO;

@Service
public interface GameService{
	
	GameDTO findOne(Integer id);
	Set<GameDTO> findAll();
	Set<GameDTO> findAllByType(SportType type);
	GameDTO createGame( GameDTO form);
	void delete(Integer Id);
	GameDTO edit(Integer id, GameDTO form);
}
