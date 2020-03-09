package com.subho.mifinityapplication.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.subho.mifinityapplication.model.CreditCard;
import com.subho.mifinityapplication.service.CreditCardService;
import com.subho.mifinityapplication.validation.Validation;

/**
 * This class is the controller for all requests related to credit card
 * functions
 * 
 * @author subasu
 * @version 1.0
 */
@Controller
public class UserCardController {

	@Autowired
	private CreditCardService creditCardService;

	@Autowired
	private Validation validation;

	@GetMapping({ "/userCardFunctions" })
	public String creditCardFunction(Model model) {
		return "userFunctions";
	}

	/**
	 * @param model this method is for the get request for the createNewCard page
	 * @return a string that redirects to the createNewCard page
	 */
	@GetMapping("/createNewCard")
	public String createNewCard(Model model) {
		model.addAttribute("createNewCard", new CreditCard());
		return "createNewCard";
	}

	/**
	 * @param userName
	 * @param creditCard
	 * @param result     this method is for the post requests for createNewCard page
	 * @return ModelAndView object and validation messages
	 */
	@PostMapping("/createNewCard")
	public ModelAndView createNewCard(@RequestParam("userName") String userName,
			@ModelAttribute("createNewCard") CreditCard creditCard, BindingResult result) {
		validation.validate(creditCard, result);
		ModelAndView modelAndView = null;
		if (result.hasErrors()) {
			modelAndView = new ModelAndView("createNewCard");
		}

		if (creditCardService.createNewCard(userName, creditCard)) {
			modelAndView = new ModelAndView("createNewCard");
			modelAndView.addObject("newCardCreated", true);
		}

		return modelAndView;

	}

	/**
	 * this method is for the get request for the searchExistingCard page
	 * 
	 * @return a string that redirects to the searchExistingCard page
	 */
	@GetMapping("/searchExistingCard")
	public String searchExistingCard() {
		return "searchExistingCard";
	}

	/**
	 * @param userName
	 * @param number   this method is for the get request for the searchExistingCard
	 *                 page
	 * @return ModelAndView object and validation messages
	 */
	@PostMapping("/searchExistingCard")
	public ModelAndView searchExistingCard(@RequestParam("userName") String userName,
			@RequestParam("number") String number) {
		ModelAndView modelAndView = new ModelAndView("updateCreditCard");
		if (!StringUtils.isEmpty(userName) && !StringUtils.isEmpty(number)) {
			List<CreditCard> creditCards = creditCardService.searchCards(userName, number);
			if (creditCards != null && !creditCards.isEmpty()) {
				modelAndView.addObject("creditCards", creditCards);
				modelAndView.addObject("updateForm", new CreditCard());
			}

		}
		return modelAndView;

	}

	/**
	 * @param userName
	 * @param card
	 * @param bindingResult this method is for the post requests for the updateCard
	 *                      page
	 * @return ModelAndView object and validation messages
	 */
	@PostMapping("/updateCard")
	public ModelAndView updateExistingCard(@RequestParam("userName") String userName,
			@ModelAttribute("updateForm") CreditCard card, BindingResult bindingResult) {
		validation.validate(card, bindingResult);
		creditCardService.updateCardExpiryDate(card);
		return new ModelAndView("userFunctions");

	}

}
