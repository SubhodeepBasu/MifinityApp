package com.subho.mifinityapplication.validation;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import com.subho.mifinityapplication.model.CreditCard;
import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.service.CreditCardService;
import com.subho.mifinityapplication.service.UserService;

/**
 * @author subasu This is the validation class that validates the inputs
 *         provided by the end user
 * @version 1.0
 */
@Component
public class Validation {

	@Autowired
	private CreditCardService cardService;

	@Autowired
	private UserService userService;

	public void validate(Object object, Errors errors) {

		if (object instanceof User) {
			User user = (User) object;

			if (StringUtils.isBlank(user.getUserName())) {
				errors.rejectValue("userName", "NotEmpty");
			} else if (user.getUserName().length() < 3 || user.getUserName().length() > 20) {
				errors.rejectValue("userName", "size.registration.userName");
			}

			if (StringUtils.isBlank(user.getPassword())) {
				errors.rejectValue("password", "NotEmpty");
			} else if (!(user.getPassword().length() >= 6)) {
				errors.rejectValue("password", "size.registration.password");
			}

			if (StringUtils.isBlank(user.getConfirmPassword())) {
				errors.rejectValue("confirmPassword", "NotEmpty");
			} else if (!user.getConfirmPassword().equals(user.getPassword())) {
				errors.rejectValue("confirmPassword", "diff.registration.confirmPassword");
			}

			if (userService.findByUserName(user.getUserName()) != null) {
				errors.rejectValue("userName", "duplicate.registration.userName");
			}
		} else {

			CreditCard creditCard = (CreditCard) object;

			if (StringUtils.isBlank(creditCard.getCardHolder())) {
				errors.rejectValue("cardHolder", "NotEmpty");
			} else if (creditCard.getCardHolder().length() < 3 || creditCard.getCardHolder().length() > 20) {
				errors.rejectValue("cardHolder", "size.createNewCard.cardHolder");
			}

			if (StringUtils.isBlank(creditCard.getNumber())) {
				errors.rejectValue("number", "NotEmpty");
			} else if (creditCard.getNumber().length() < 8 || creditCard.getNumber().length() > 19) {
				errors.rejectValue("number", "size.createNewCard.number");
			}

			if (StringUtils.isBlank(creditCard.getExpiryDate())) {
				errors.rejectValue("expiryDate", "NotEmpty");
			} else if (!(creditCard.getExpiryDate().matches("(?:0[1-9]|1[0-2])/[0-9]{2}"))) {
				errors.rejectValue("expiryDate", "format.createNewCard.expiryDate");
			}

			if (cardService.findByCardNumber(creditCard.getNumber()) != null) {
				errors.rejectValue("number", "duplicate.createNewCard.number");
			}
		}

	}

}
