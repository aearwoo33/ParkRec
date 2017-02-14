package com.aearwood.coordinator.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.helper.ConverterUtil;
import com.aearwood.coordinator.domain.Athlete;
import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.domain.User;
import com.aearwood.coordinator.dto.AthleteDTO;
import com.aearwood.coordinator.repositories.AthleteRepository;
import com.aearwood.coordinator.repositories.UserRepository;
import com.aearwood.coordinator.service.AthleteService;

@Service
public class AthleteServiceImpl implements AthleteService {

	@Autowired 
	AthleteRepository athleteRepository;

	@Autowired
	UserRepository userRepository;
	
	@Override
	public AthleteDTO createAthlete(AthleteDTO athleteDTO, String parentEmail) {
		Optional<User> user = userRepository.findOneByEmail(parentEmail);
		Athlete athlete = null;
		if (user.isPresent()) {
			athlete = new Athlete();
			athlete.setAge(athleteDTO.getAge());
			athlete.setFirstName(athleteDTO.getFirstName());
			athlete.setLastName(athleteDTO.getLastName());
			athlete.setUserId(user.get().getId());
		}
		else {
			throw new SessionAuthenticationException(String.format("Invalid Session:  User not found for %s", parentEmail));
		}
		AthleteDTO returnedAthleteDTO = ConverterUtil.convertToDTO(athleteRepository.save(athlete));
		return returnedAthleteDTO;
	}

	@Override
	public AthleteDTO findOne(Integer id) {
		AthleteDTO athleteDTO = ConverterUtil.convertToDTO( athleteRepository.findOne(id));
		return athleteDTO;
	}

	@Override
	public void delete(Integer Id) {
		athleteRepository.delete(Id);
	}

	@Override
	@Transactional
	public AthleteDTO edit(Integer athleteId, AthleteDTO athleteDTO) {
		Athlete athlete = athleteRepository.findOne(athleteId);
		athlete.setAge(athleteDTO.getAge());
		athlete.setFirstName(athleteDTO.getFirstName());
		athlete.setLastName(athleteDTO.getLastName());
		AthleteDTO athleteDTOreturn = ConverterUtil.convertToDTO( athlete);
		return athleteDTOreturn;
	}

	@Override
	public Set<AthleteDTO> findAll() {
		Set <Athlete> athleteSet = new HashSet<>();
		athleteRepository.findAll().forEach(athleteSet::add);
		return ConverterUtil.convertToAthleteDTOSet(athleteSet);
	}
	
}
