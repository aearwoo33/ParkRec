package com.aearwood.coordinator.service.impl;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aearwood.coordinator.domain.Athlete;
import com.aearwood.coordinator.domain.Coach;
import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.domain.Team;
import com.aearwood.coordinator.domain.User;
import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.dto.TeamDTO;
import com.aearwood.coordinator.helper.ConverterUtil;
import com.aearwood.coordinator.repositories.AthleteRepository;
import com.aearwood.coordinator.repositories.CoachRepository;
import com.aearwood.coordinator.repositories.TeamRepository;
import com.aearwood.coordinator.service.TeamService;

@Service
public class TeamServiceImpl implements TeamService {

	@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	AthleteRepository athleteRepository;
	
	@Autowired 
	CoachRepository coachRepository;
	
	@Override
	public TeamDTO createTeam(TeamDTO teamDTO) {
		Team team = new Team();
		
		team.setMaximumAge(teamDTO.getMaximumAge());
		team.setMinimumAge(teamDTO.getMinimumAge());
		team.setName(teamDTO.getName());
		team.setType(teamDTO.getType());
		teamRepository.save(team);
		
		return ConverterUtil.convertToDTO(team);
	}
	@Transactional
	@Override
	public void addAthlete(Integer athleteId, Integer id) {
		Set<Athlete> athletes = teamRepository.findOne(id).getAthletes();
		Athlete athlete = athleteRepository.findOne(athleteId);
		athletes.add(athlete);
	}
	
	@Transactional
	@Override
	public void addCoach(Integer coachId, Integer id) {
		Set<Coach> coaches = teamRepository.findOne(id).getCoaches();
		Coach coach = coachRepository.findOne(coachId);
		coaches.add(coach);
	}

	@Override
	public TeamDTO findOne(Integer id) {
		return ConverterUtil.convertToDTO(teamRepository.findOne(id));
	}

	@Override
	public void delete(Integer id) {
		teamRepository.delete(id);
	}

	@Override
	public TeamDTO edit(Integer id, TeamDTO teamDTO) {
		Team team = teamRepository.findOne(id);
		team.setMaximumAge(teamDTO.getMaximumAge());
		team.setMinimumAge(teamDTO.getMinimumAge());
		team.setName(teamDTO.getName());
		team.setType(teamDTO.getType());
		return ConverterUtil.convertToDTO(team);
	}

	@Override
	public Set<TeamDTO> findAll() {
		Set<Team> teamSet = new HashSet<>();
		teamRepository.findAll().forEach(teamSet::add);
		Set<TeamDTO> teamDTOset = null;
		if (teamSet != null) {
			teamDTOset = ConverterUtil.convertToTeamDTOSet(teamSet);
		}
		return teamDTOset ;
	}
	@Override
	public Set<TeamDTO> findAllByType(SportType type) {
		Set<Team> teamSet = new HashSet<>();
		teamRepository.findAllByType(type).forEach(teamSet::add);
		
		Set<TeamDTO> teamDTOset = null;
		if (teamSet != null) {
			teamDTOset = ConverterUtil.convertToTeamDTOSet(teamSet);
		}
		return teamDTOset ;
	}

}
