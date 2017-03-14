package com.aearwood.coordinator.service.impl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.aearwood.coordinator.domain.Game;
import com.aearwood.coordinator.domain.SportType;
import com.aearwood.coordinator.dto.GameDTO;
import com.aearwood.coordinator.helper.ConverterUtil;
import com.aearwood.coordinator.repositories.GameRepository;
import com.aearwood.coordinator.service.GameService;

@Service
public class GameServiceImpl implements GameService {

	/*@Autowired
	TeamRepository teamRepository;
	
	@Autowired
	AthleteRepository athleteRepository;
	*/
	@Autowired 
	GameRepository gameRepository;

	@Override
	public GameDTO findOne(Integer id) {
		Game game = gameRepository.findOne(id);
		return ConverterUtil.convertToDTO(game);
	}

	@Override
	public Set<GameDTO> findAll() {
		Set<Game> gameSet = new HashSet<>();
		gameRepository.findAll().forEach(gameSet::add);
		Set<GameDTO> gameDTOset = null;
		if (gameSet != null) {
			gameDTOset = ConverterUtil.convertToGameDTOSet(gameSet);
		}
		return gameDTOset;
	}

	@Override
	public Set<GameDTO> findAllByType(SportType type) {
		Set<Game> gameSet = new HashSet<>();
		gameRepository.findAllByType(type).forEach(game->{
			if(type.equals(game.getType())){
				gameSet.add(game);
			}
		});
		Set<GameDTO> gameDTOset = null;
		if (gameSet != null) {
			gameDTOset = ConverterUtil.convertToGameDTOSet(gameSet);
		}
		return gameDTOset;
	}

	@Override
	public GameDTO createGame(GameDTO gameDTO) {
		Game game = new Game();
		game.setAwayTeam(gameDTO.getAwayTeam());
		game.setDateTime(gameDTO.getDateTime());
		game.setHomeTeam(game.getHomeTeam());
		game.setId(gameDTO.getId());
		game.setRefereeId(gameDTO.getRefereeId());
		game.setType(gameDTO.getType());
		game = gameRepository.save(game);
		return ConverterUtil.convertToDTO(game);
	}

	@Override
	public void delete(Integer id) {
		gameRepository.delete(id);
		
	}

	@Override
	public GameDTO edit(Integer id, GameDTO gameDTO) {
		Game game = gameRepository.findOne(id);
		if (game != null) {
			game.setAwayTeam(gameDTO.getAwayTeam());
			game.setDateTime(gameDTO.getDateTime());
			game.setHomeTeam(game.getHomeTeam());
			game.setRefereeId(gameDTO.getRefereeId());
			game.setType(gameDTO.getType());
			game = gameRepository.save(game);
		}
		return ConverterUtil.convertToDTO(game);
	}

	
	
}
