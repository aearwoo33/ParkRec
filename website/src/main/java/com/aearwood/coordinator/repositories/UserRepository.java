package com.aearwood.coordinator.repositories;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

import com.aearwood.coordinator.domain.User;

public interface UserRepository extends CrudRepository<User, Integer>{
	Optional<User> findOneByEmail(String email);
}
