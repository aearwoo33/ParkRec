package com.aearwood.coordinator.dto;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.aearwood.coordinator.domain.SportType;

public class GameDTO implements Serializable{

	@NotNull
	private Integer id;
	
	@NotNull
	private Date dateTime;

	@NotNull
	private Integer  homeTeam;

	@NotNull
	private Integer awayTeam;

	@NotNull
	private Integer refereeId;
	
	@NotNull
	private SportType type;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Date getDateTime() {
		return dateTime;
	}
	public void setDateTime(Date dateTime) {
		this.dateTime = dateTime;
	}
	public Integer getHomeTeam() {
		return homeTeam;
	}
	public void setHomeTeam(Integer homeTeam) {
		this.homeTeam = homeTeam;
	}
	public Integer getAwayTeam() {
		return awayTeam;
	}
	public void setAwayTeam(Integer awayTeam) {
		this.awayTeam = awayTeam;
	}
	public Integer getRefereeId() {
		return refereeId;
	}
	public void setRefereeId(Integer refereeId) {
		this.refereeId = refereeId;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((awayTeam == null) ? 0 : awayTeam.hashCode());
		result = prime * result + ((dateTime == null) ? 0 : dateTime.hashCode());
		result = prime * result + ((homeTeam == null) ? 0 : homeTeam.hashCode());
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		result = prime * result + ((refereeId == null) ? 0 : refereeId.hashCode());
		result = prime * result + ((type == null) ? 0 : type.hashCode());
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
		GameDTO other = (GameDTO) obj;
		if (awayTeam == null) {
			if (other.awayTeam != null)
				return false;
		} else if (!awayTeam.equals(other.awayTeam))
			return false;
		if (dateTime == null) {
			if (other.dateTime != null)
				return false;
		} else if (!dateTime.equals(other.dateTime))
			return false;
		if (homeTeam == null) {
			if (other.homeTeam != null)
				return false;
		} else if (!homeTeam.equals(other.homeTeam))
			return false;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		if (refereeId == null) {
			if (other.refereeId != null)
				return false;
		} else if (!refereeId.equals(other.refereeId))
			return false;
		if (type != other.type)
			return false;
		return true;
	}
	public SportType getType() {
		return type;
	}
	public void setType(SportType type) {
		this.type = type;
	}
}
