package com.aearwood.coordinator.dto;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

public class CoachDTO {
	private Integer id;

	private Long userId; 

	@NotEmpty
	private String firstName;

	@NotEmpty
	private String lastName;
	
	@NotNull
	private Integer ageGroupMaximum;
	
	@NotNull
	private Integer ageGroupMinimum;

	@NotEmpty
	private TeamDTO team;

	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getFirstName() {
		return firstName;
	}
	public Long getUserId() {
		return userId;
	}
	public void setUserId(Long userId) {
		this.userId = userId;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public Integer getAgeGroupMaximum() {
		return ageGroupMaximum;
	}
	public void setAgeGroupMaximum(Integer ageGroupMaximum) {
		this.ageGroupMaximum = ageGroupMaximum;
	}
	public Integer getAgeGroupMinimum() {
		return ageGroupMinimum;
	}
	public void setAgeGroupMinimum(Integer ageGroupMinimum) {
		this.ageGroupMinimum = ageGroupMinimum;
	}
	public TeamDTO getTeam() {
		return team;
	}
	public void setTeam(TeamDTO team) {
		this.team = team;
	}

}
