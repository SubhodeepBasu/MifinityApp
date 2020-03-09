package com.subho.mifinityapplication.controller;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.service.UserService;
import com.subho.mifinityapplication.validation.Validation;

/**
 * This class is the controller for all requests related to user login and
 * registration
 * 
 * @author subasu
 * @version 1.0
 */
@Controller
public class UserController {

	@Autowired
	UserService userService;

	@Autowired
	private Validation validation;

	/**
	 * @param error This method is for the get requests for the login page
	 * @return ModelAndView object
	 */
	@GetMapping(value = "/login")
	public ModelAndView login(String error) {
		ModelAndView modelAndView = new ModelAndView("login");
		if (StringUtils.isNotBlank(error)) {
			modelAndView.addObject("error", "Invalid Username or Password");
		}

		return modelAndView;
	}

	/**
	 * @param model This method is for the get requests for the userRegistration
	 *              page
	 * @return userRegistration page name
	 */
	@GetMapping("/userRegistration")
	public String userRegistration(Model model) {
		model.addAttribute("registration", new User());
		return "userRegistration";
	}

	/**
	 * @param registrationDetails
	 * @param result              This method is for the post requests for the
	 *                            userRegistration page
	 * @return
	 */
	@PostMapping("/userRegistration")
	public ModelAndView userRegistration(@ModelAttribute("registration") User registrationDetails,
			BindingResult result) {
		validation.validate(registrationDetails, result);
		if (result.hasErrors()) {
			return new ModelAndView("userRegistration");
		}
		if (userService.registerUser(registrationDetails)) {
			userService.userAutoLogin(registrationDetails.getUserName(), registrationDetails.getPassword());
			return new ModelAndView("userFunctions");
		} else {
			return new ModelAndView("error").addObject("errorMessage", "Error in registering your details");
		}
	}

}
