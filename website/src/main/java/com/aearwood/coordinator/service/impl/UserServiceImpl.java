package com.aearwood.coordinator.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.aearwood.coordinator.domain.User;
import com.aearwood.coordinator.dto.UserCreateForm;
import com.aearwood.coordinator.repositories.UserRepository;
import com.aearwood.coordinator.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public Optional<User> getUserById(Integer id) {
		return Optional.ofNullable(userRepository.findOne(id));
	}

	@Override
	public Optional<User> getUserByEmail(String email) {
		return userRepository.findOneByEmail(email);
	}

	@Override
	public Optional<User> createUser(UserCreateForm form) {
		User user=null;
		if (form.getPassword().isEmpty()) {
			// No password
		}
		else if (! form.getPassword().equals(form.getPasswordRepeated())) {
			// Repeated passwords do not match
		}
		else {
			user = new User();
			user.setEmail(form.getEmail());
			user.setFirstName(form.getFirstName());
			user.setLastName(form.getLastName());
			user.setRole(form.getRole());
			user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
			user.setPhoneNumber(form.getPhoneNumber());
			userRepository.save(user);
		}
		return Optional.ofNullable(user);
	}
	
	@Override
	@Transactional
	public Optional<User> editUser(UserCreateForm form) {
		Optional<User> userOpt=null;
		if (form.getPassword().isEmpty()) {
			// No password
			userOpt = Optional.empty();
		}
		else if (! form.getPassword().equals(form.getPasswordRepeated())) {
			// Repeated passwords do not match
			userOpt = Optional.empty();
		}
		else {
			userOpt= userRepository.findOneByEmail(form.getEmail());
			if (userOpt.isPresent()) {
				User user = userOpt.get();
				user.setEmail(form.getEmail());
				user.setFirstName(form.getFirstName());
				user.setLastName(form.getLastName());
				user.setRole(form.getRole());
				user.setPasswordHash(new BCryptPasswordEncoder().encode(form.getPassword()));
				user.setPhoneNumber(form.getPhoneNumber());
			}
		}
		return userOpt;
	}

}
