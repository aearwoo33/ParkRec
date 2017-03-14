package com.aearwood.coordinator.helper;

import java.util.HashSet;
import java.util.Set;

import com.aearwood.coordinator.domain.Athlete;
import com.aearwood.coordinator.domain.Coach;
import com.aearwood.coordinator.domain.Game;
import com.aearwood.coordinator.domain.Team;
import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.dto.CoachDTO;
import com.aearwood.coordinator.dto.GameDTO;
import com.aearwood.coordinator.dto.TeamDTO;

public class ConverterUtil {
	public static AthleteDTO convertToDTO(Athlete athlete) {
		AthleteDTO athleteDTO = new AthleteDTO();
		athleteDTO.setAge(athlete.getAge());
		athleteDTO.setFirstName(athlete.getFirstName());
		athleteDTO.setLastName(athlete.getLastName());
		if (athlete.getTeams() != null) {
			athleteDTO.setTeams(convertToTeamDTOSet(athlete.getTeams()));
		}
		athleteDTO.setId(athlete.getId());
		athleteDTO.setUserId(athlete.getUserId());
		return athleteDTO;
	}
	public static GameDTO convertToDTO(Game  game) {
		GameDTO gameDTO = new GameDTO();
		gameDTO.setAwayTeam(game.getAwayTeam());
		gameDTO.setDateTime(game.getDateTime());
		gameDTO.setHomeTeam(game.getHomeTeam());
		gameDTO.setRefereeId(game.getRefereeId());
		gameDTO.setId(game.getId());
		return gameDTO;
	}
	public static TeamDTO convertToDTO(Team team) {
		TeamDTO teamDTO = new TeamDTO();
		teamDTO.setMaximumAge(team.getMaximumAge());
		teamDTO.setMinimumAge(team.getMinimumAge());
		teamDTO.setName(team.getName());
		teamDTO.setType(team.getType());
		teamDTO.setId(team.getId());
		if (team.getCoaches() != null) {
			teamDTO.setCoaches(convertToCoachDTOSet(team.getCoaches()));
		}
		return teamDTO;
	}
	public static CoachDTO convertToDTO(Coach coach) {
		CoachDTO coachDTO = new CoachDTO();
		coachDTO.setFirstName(coach.getFirstName());
		coachDTO.setLastName(coach.getLastName());
		coachDTO.setAgeGroupMaximum(coach.getAgeGroupMaximum());
		coachDTO.setAgeGroupMinimum(coach.getAgeGroupMinimum());
		coachDTO.setId(coach.getId());
		coachDTO.setTeam(convertToDTO(coach.getTeam()));
		coachDTO.setUserId(coach.getUserId());
		return coachDTO;
	}
	public static Set<AthleteDTO> convertToAthleteDTOSet(Set<Athlete> athleteSet) {
		Set<AthleteDTO> athleteDTOSet =  new HashSet<>();
		for (Athlete athlete : athleteSet) {
			AthleteDTO athleteDTO = convertToDTO(athlete);
			athleteDTOSet.add(athleteDTO);
		}
		return athleteDTOSet;

	}
	public static Set<TeamDTO> convertToTeamDTOSet(Set<Team> teamSet) {
		Set<TeamDTO> teamDTOSet =  new HashSet<>();
		for (Team team : teamSet) {
			TeamDTO teamDTO = convertToDTO(team);
			teamDTOSet.add(teamDTO);
		}
		return teamDTOSet;

	}
	public static Set<CoachDTO> convertToCoachDTOSet(Set<Coach> coachSet) {
		Set<CoachDTO> coachDTOSet =  new HashSet<>();
		for (Coach coach : coachSet) {
			CoachDTO teamDTO = convertToDTO(coach);
			coachDTOSet.add(teamDTO);
		}
		return coachDTOSet;

	}
	
	public static Set<GameDTO> convertToGameDTOSet(Set<Game> gameSet) {
		Set<GameDTO> gameDTOset =  new HashSet<>();
		for (Game game : gameSet) {
			GameDTO gameDTO = convertToDTO(game);
			gameDTOset.add(gameDTO);
		}
		return gameDTOset;

	}
}
