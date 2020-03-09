package com.subho.mifinityapplication.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.subho.mifinityapplication.model.CreditCard;
import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.repository.CreditCardRepository;
import com.subho.mifinityapplication.repository.UserRepository;
import com.subho.mifinityapplication.service.CreditCardService;

/**
 * @author subasu This is the implementation class for the CreditCardService
 *         interface. It has the implemented and overridden methods of the
 *         interface
 * @version 1.0
 */
@Service
public class CreditCardServiceImpl implements CreditCardService {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private CreditCardRepository creditCardRepo;

	@Value("${admin.role}")
	private String adminRole;

	@Value("${user.role}")
	private String userRole;

	/**
	 * @param userName
	 * @param creditCard This method creates a new card by passing a username and
	 *                   CreditCard object to the save method of the credit card
	 *                   repository
	 * @return boolean
	 */
	@Override
	public boolean createNewCard(String userName, CreditCard creditCard) {
		User user = userRepository.findByUserName(userName);

		if (user != null) {
			creditCard.setUser(user);
			creditCardRepo.save(creditCard);
			return true;
		}
		return false;
	}

	/**
	 * @param userName
	 * @param number   This method searches all the cards created by a particular
	 *                 user of a particular number pattern by calling the
	 *                 findAllCards method of the credit card repository
	 * @return CreditCard
	 */
	@Override
	public List<CreditCard> searchCards(String userName, String number) {
		List<CreditCard> creditCards = null;
		User currentUser = userRepository.findByUserName(userName);

		if (currentUser.getRoles().stream().filter(role -> role.getName().equals(userRole)).count() > 0) {
			creditCards = creditCardRepo.findCreditCardsForUser(userName, number);
		} else {
			creditCards = creditCardRepo.findAllCards(number);
		}
		return creditCards;
	}

	/**
	 * @param card This method updates the expiry date of a card by invoking the
	 *             updateCardExpiryDate method of the credit card repository
	 * @return Integer
	 */
	@Override
	public void updateCardExpiryDate(CreditCard card) {
		creditCardRepo.updateCardExpiryDate(card.getNumber(), card.getExpiryDate());

	}

	/**
	 * @param number This method finds a card based on its number by invoking the
	 *               findByNumber method of the credit card repository
	 * @return Credit Card
	 */
	
	 @Override 
	 public CreditCard findByCardNumber(String number) {
	  return creditCardRepo.findByNumber(number); 
	  }


}
