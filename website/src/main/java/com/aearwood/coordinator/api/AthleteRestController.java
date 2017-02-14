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
import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.dto.TeamDTO;
import com.aearwood.coordinator.service.AthleteService;

@RestController
@RequestMapping("/api/v1/sports/athletes")
public class AthleteRestController {
	@Autowired
	AthleteService athleteService;
	
	@RequestMapping(method = RequestMethod.GET)
	public ResponseEntity<Set<AthleteDTO>> readAthletes() {
		return new ResponseEntity<Set<AthleteDTO>>(athleteService.findAll(), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{athleteId}")
	public ResponseEntity<AthleteDTO> readAthlete( @PathVariable Integer athleteId) {
		return new ResponseEntity<AthleteDTO>(athleteService.findOne(athleteId), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.GET, value = "/{athleteId}/teams")
	public ResponseEntity<Set<TeamDTO>> readTeams( @PathVariable Integer athleteId) {
		return new ResponseEntity<Set<TeamDTO>>(athleteService.findOne(athleteId).getTeams(), HttpStatus.OK);
	}
	@RequestMapping(method = RequestMethod.POST) // Need to add Validation
	public ResponseEntity<AthleteDTO> createAthlete(@RequestBody AthleteDTO athleteDTO) {
		PrincipalUser userDetails = (PrincipalUser) authentication.getPrincipal();
		String email = userDetails.getUsername();
		AthleteDTO athleteDTOresponse = athleteService.createAthlete(athleteDTO, email);
		return new ResponseEntity<AthleteDTO>(athleteDTOresponse, HttpStatus.CREATED);
	}
	@RequestMapping(method = RequestMethod.PUT, value = "/{athleteId}") // Need to add Validation
	public String editAthlete(@PathVariable Integer athleteId,@RequestBody AthleteDTO athleteDTO) {
		 athleteService.edit(athleteId, athleteDTO);
		return "redirect:\"/api/v1/sports/athletes\"" + athleteId;
	}
}
