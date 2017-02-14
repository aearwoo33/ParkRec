package com.aearwood.coordinator.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

import com.aearwood.coordinator.domain.SportType;

public class TeamDTO{
	private Integer id;
	
	@NotNull
	private String name;

	@NotEmpty
	private Set<CoachDTO> coaches;
	
	@NotEmpty
	private SportType type;

	@NotEmpty
	private Set<AthleteDTO> athletes;
	
	@NotNull
	private Integer minimumAge;
	
	@NotNull
	private Integer maximumAge;

	public Set<AthleteDTO> getAthletes() {
		return athletes;
	}
	public void setAthletes(Set<AthleteDTO> athletes) {
		this.athletes = athletes;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public SportType getType() {
		return type;
	}
	public void setType(SportType type) {
		this.type = type;
	}
	public Integer getMinimumAge() {
		return minimumAge;
	}
	public void setMinimumAge(Integer minimumAge) {
		this.minimumAge = minimumAge;
	}
	public Integer getMaximumAge() {
		return maximumAge;
	}
	public void setMaximumAge(Integer maximumAge) {
		this.maximumAge = maximumAge;
	}
	public Set<CoachDTO> getCoaches() {
		return coaches;
	}
	public void setCoaches(Set<CoachDTO> coaches) {
		this.coaches = coaches;
	}

}
