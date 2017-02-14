package com.aearwood.coordinator.domain;

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

@Entity
@Table(name = "coach")
public class Coach implements Serializable{
	
	private static final long serialVersionUID = -1567313210060737757L;

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;

	// Related user account
	@Column(name = "user_id", nullable = false)
	private Long userId; 

	@Column(name = "first_name", nullable = false)
	private String firstName;

	@Column(name = "last_name", nullable = false)
	private String lastName;

	private Integer ageGroupMaximum;
	private Integer ageGroupMinimum;

	@ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(name="team_id")
	private Team team;

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
	public Team getTeam() {
		return team;
	}
	public void setTeam(Team team) {
		this.team = team;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((ageGroupMaximum == null) ? 0 : ageGroupMaximum.hashCode());
		result = prime * result + ((ageGroupMinimum == null) ? 0 : ageGroupMinimum.hashCode());
		result = prime * result + ((firstName == null) ? 0 : firstName.hashCode());
		result = prime * result + ((lastName == null) ? 0 : lastName.hashCode());
		result = prime * result + ((team == null) ? 0 : team.hashCode());
		result = prime * result + ((userId == null) ? 0 : userId.hashCode());
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
		Coach other = (Coach) obj;
		if (ageGroupMaximum == null) {
			if (other.ageGroupMaximum != null)
				return false;
		} else if (!ageGroupMaximum.equals(other.ageGroupMaximum))
			return false;
		if (ageGroupMinimum == null) {
			if (other.ageGroupMinimum != null)
				return false;
		} else if (!ageGroupMinimum.equals(other.ageGroupMinimum))
			return false;
		if (firstName == null) {
			if (other.firstName != null)
				return false;
		} else if (!firstName.equals(other.firstName))
			return false;
		if (lastName == null) {
			if (other.lastName != null)
				return false;
		} else if (!lastName.equals(other.lastName))
			return false;
		if (team == null) {
			if (other.team != null)
				return false;
		} else if (!team.equals(other.team))
			return false;
		if (userId == null) {
			if (other.userId != null)
				return false;
		} else if (!userId.equals(other.userId))
			return false;
		return true;
	}

}
