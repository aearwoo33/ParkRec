package com.aearwood.coordinator.domain;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Table;

@Embeddable
@Table(name = "team_athlete_key")
public class TeamAthleteKey implements Serializable{
	
	private static final long serialVersionUID = 4902048862582696634L;
	
	@Column(name = "team_id", nullable = false)
	private Integer teamId;
	
	@Column(name = "athlete_id", nullable = false)
	 private Integer athleteId;

	public Integer getTeamId() {
		return teamId;
	}

	public void setTeamId(Integer teamId) {
		this.teamId = teamId;
	}

	public Integer getAthleteId() {
		return athleteId;
	}

	public void setAthleteId(Integer athleteId) {
		this.athleteId = athleteId;
	}
	

	  
}
