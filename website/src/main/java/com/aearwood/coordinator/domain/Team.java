package com.aearwood.coordinator.domain;

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

@Entity
@Table(name = "team")
public class Team implements Serializable{
	
	private static final long serialVersionUID = -5643578939688312122L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String name;

	@OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL, mappedBy="team")
	private Set<Coach> coaches;
	
	private SportType type;

	@ManyToMany(cascade = CascadeType.ALL)
	@JoinTable(name = "team_athlete", joinColumns = @JoinColumn(name = "team_id", referencedColumnName = "id"), inverseJoinColumns = @JoinColumn(name = "athlete_id", referencedColumnName = "id"))
	private Set<Athlete> athletes;
	private Integer minimumAge;
	private Integer maximumAge;


	public Set<Athlete> getAthletes() {
		return athletes;
	}
	public void setAthletes(Set<Athlete> athletes) {
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
	public Set<Coach> getCoaches() {
		return coaches;
	}
	public void setCoaches(Set<Coach> coaches) {
		this.coaches = coaches;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((athletes == null) ? 0 : athletes.hashCode());
		result = prime * result + ((coaches == null) ? 0 : coaches.hashCode());
		result = prime * result + ((maximumAge == null) ? 0 : maximumAge.hashCode());
		result = prime * result + ((minimumAge == null) ? 0 : minimumAge.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
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
		Team other = (Team) obj;
		if (athletes == null) {
			if (other.athletes != null)
				return false;
		} else if (!athletes.equals(other.athletes))
			return false;
		if (coaches == null) {
			if (other.coaches != null)
				return false;
		} else if (!coaches.equals(other.coaches))
			return false;
		if (maximumAge == null) {
			if (other.maximumAge != null)
				return false;
		} else if (!maximumAge.equals(other.maximumAge))
			return false;
		if (minimumAge == null) {
			if (other.minimumAge != null)
				return false;
		} else if (!minimumAge.equals(other.minimumAge))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (type != other.type)
			return false;
		return true;
	}

}
