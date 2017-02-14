package com.aearwood.coordinator.service;

import java.util.Set;

import org.springframework.stereotype.Service;

import com.aearwood.coordinator.dto.AthleteDTO;

@Service
public interface AthleteService{
	AthleteDTO findOne(Integer id);
	void delete(Integer Id);
	AthleteDTO edit(Integer athleteId, AthleteDTO athleteDTO);
	Set<AthleteDTO> findAll();
	AthleteDTO createAthlete(AthleteDTO athleteDTO, String parentEmail);
}
