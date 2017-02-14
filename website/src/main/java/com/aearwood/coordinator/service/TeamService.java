package com.aearwood.coordinator.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.dto.TeamDTO;

@Service
public interface TeamService{
	TeamDTO createTeam(TeamDTO form);
	void addAthlete(Integer athleteId, Integer id);
	TeamDTO findOne(Integer id);
	void delete(Integer Id);
	TeamDTO edit(Integer id, TeamDTO form);
	Set<TeamDTO> findAll();
	Set<TeamDTO> findAllByType(SportType type);
	void addCoach(Integer coachId, Integer id);
}
