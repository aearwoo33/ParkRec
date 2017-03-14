package com.aearwood.coordinator.api;

import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.aearwood.coordinator.domain.PrincipalUser;
import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.dto.CoachDTO;
import com.aearwood.coordinator.dto.TeamDTO;
import com.aearwood.coordinator.service.AthleteService;
import com.aearwood.coordinator.service.TeamService;

@RestController
@RequestMapping("/api/v1/sports/{type}/games")
public class GameRestController {
	@Autowired
	TeamService teamService;
	
@RequestMapping(method = RequestMethod.GET)
public ResponseEntity<Set<TeamDTO>> readAll(@PathVariable SportType type) {
	Set<TeamDTO> teamDTO = teamService.findAllByType(type);
	return new ResponseEntity<Set<TeamDTO>>(teamDTO, HttpStatus.OK);
}
@RequestMapping(method = RequestMethod.GET, value = "{teamId}")
public ResponseEntity<TeamDTO> readTeam(@PathVariable SportType type, @PathVariable Integer teamId) {
	TeamDTO teamDTO = teamService.findOne(teamId);
	return new ResponseEntity<TeamDTO>(teamDTO, HttpStatus.OK);
}
@RequestMapping(method = RequestMethod.GET, value = "{teamId}/coaches")
public ResponseEntity<Set<CoachDTO>> readCoaches(@PathVariable SportType type, @PathVariable Integer teamId, @PathVariable String coaches) {
	Set<CoachDTO> coachSet = teamService.findOne(teamId).getCoaches();
	return new ResponseEntity<Set<CoachDTO>>(coachSet,HttpStatus.OK);
}
@RequestMapping(method = RequestMethod.GET, value = "{teamId}/athletes")
public ResponseEntity<Set<AthleteDTO>> readAthletes(@PathVariable SportType type, @PathVariable Integer teamId, @PathVariable String coaches) {
	Set<AthleteDTO> athleteSet = teamService.findOne(teamId).getAthletes();
	return new ResponseEntity<Set<AthleteDTO>>(athleteSet,HttpStatus.OK);
}
@RequestMapping(method = RequestMethod.POST) // Need to add Validation
public ResponseEntity<TeamDTO> createTeam(@PathVariable SportType type, @RequestBody TeamDTO teamDTO) {
	TeamDTO teamDTOresponse = teamService.createTeam(teamDTO);
	return new ResponseEntity<TeamDTO>(teamDTOresponse, HttpStatus.CREATED);
}
}
