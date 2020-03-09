package com.subho.mifinityapplication.service;

import java.util.List;

import com.subho.mifinityapplication.model.CreditCard;

/**
 * @author subasu This is the service interface for credit card functions. It
 *         has the business logic connecting the controllers to the repositories
 * @version 1.0
 */
public interface CreditCardService {

	public boolean createNewCard(String userName, CreditCard creditCard);

	public List<CreditCard> searchCards(String userName, String number);

	public void updateCardExpiryDate(CreditCard card);

	public CreditCard findByCardNumber(String number);

}
