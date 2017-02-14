package com.aearwood.coordinator.web;

import java.util.NoSuchElementException;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.servlet.ModelAndView;

import com.aearwood.coordinator.domain.User;
import com.aearwood.coordinator.dto.UserCreateForm;
import com.aearwood.coordinator.service.UserService;

@Controller
public class UserController {
	private static final Logger logger =
			LoggerFactory.getLogger(UserController.class);

	@Autowired
	private  UserService userService;

	@RequestMapping(value = "/createUser", method = RequestMethod.GET)
	public ModelAndView createUserForm() {
		Optional<User> user = Optional.ofNullable(new User());
		return new ModelAndView("views/createUser", "user", user.get());
	}
	@RequestMapping(value = "/createUser", method = RequestMethod.POST)
 	public ModelAndView createUser(@ModelAttribute("form") UserCreateForm form) {
		Optional<User> user = userService.createUser(form);
		return new ModelAndView("views/createUser", "user", user.get());
	}
	@RequestMapping(value = "/user/{email}/edit", method = RequestMethod.GET)
	public ModelAndView user(@PathVariable Optional<String> email) {
		Optional<User> user;
		if (email.isPresent()) {
			user = userService.getUserByEmail(email.get());
		}
		else {
			user = Optional.ofNullable(new User());
		}
		return new ModelAndView("views/editUser", "user", user.get());
	}
	@RequestMapping(value = "/user/{email}/edit", method = RequestMethod.POST)
	public ModelAndView user( @ModelAttribute("form") UserCreateForm form, @PathVariable Optional<String> email) {
		Optional<User> user = null;
		if (email.isPresent()) {
			if (email.get().equals(form.getEmail())){
				user = userService.editUser(form);
			}
			else {
				throw (new IllegalArgumentException(email.get() + ": path variable does not match posted form. Email address can not be changed."));
			}
		}
		else {
			throw (new IllegalArgumentException("Email address can not be null."));
			
		}
		return new ModelAndView("views/editUser", "user", user.orElseThrow(() -> new NoSuchElementException(form.getEmail() + " Not Found!")));
	}
}
