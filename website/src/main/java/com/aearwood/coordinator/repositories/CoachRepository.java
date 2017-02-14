package com.aearwood.coordinator.repositories;

import com.aearwood.coordinator.domain.Coach;
import com.aearwood.coordinator.domain.SportType;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface CoachRepository extends CrudRepository<Coach, Integer>{
}
