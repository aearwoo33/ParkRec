package com.aearwood.coordinator.domain;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.MapsId;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "team_athlete")
public class TeamAthlete implements Serializable{

	private static final long serialVersionUID = -7201702831186205389L;

	@EmbeddedId
	  private TeamAthleteKey  id;
	  
	@OneToOne
	@JoinColumn(name = "team_id" , insertable=false, updatable =false)
	  private Team team;
	  
	@OneToOne
	@JoinColumn(name = "athlete_id" , insertable=false, updatable =false)
	  private Athlete athlete;
	  
	public TeamAthleteKey getId() {
		return id;
	}
	public void setId(TeamAthleteKey id) {
		this.id = id;
	}
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	public Athlete getAthlete() {
		return athlete;
	}
	public void setAthlete(Athlete athlete) {
		this.athlete = athlete;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((athlete == null) ? 0 : athlete.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TeamAthlete other = (TeamAthlete) obj;
		if (athlete == null) {
			if (other.athlete != null)
				return false;
		} else if (!athlete.equals(other.athlete))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		return true;
	}
	
	
}
