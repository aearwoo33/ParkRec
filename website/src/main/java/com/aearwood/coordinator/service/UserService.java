package com.aearwood.coordinator.service;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.aearwood.coordinator.domain.User;
import com.aearwood.coordinator.dto.UserCreateForm;

@Service
public interface UserService {
		Optional<User> getUserById ( Integer id);
		Optional<User> getUserByEmail(String email);
		Optional<User> editUser(UserCreateForm form);
		Optional<User> createUser(UserCreateForm form);
}
