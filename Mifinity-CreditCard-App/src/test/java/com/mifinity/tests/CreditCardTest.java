package com.mifinity.tests;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.subho.mifinityapplication.model.CreditCard;
import com.subho.mifinityapplication.model.Role;
import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.repository.CreditCardRepository;
import com.subho.mifinityapplication.serviceImpl.CreditCardServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class CreditCardTest {

	@InjectMocks
	private CreditCardServiceImpl creditCardServiceImpl;

	@Mock
	private CreditCardRepository creditCardRepository;

	@Mock
	private BCryptPasswordEncoder encoder;

	@Value("${user.role}")
	private String userRole;
	
	private CreditCard testCard;
	
	private User testUser;

	@Before
	public void setUp() {
		Role testRole = new Role(userRole);
		String password = encoder.encode("Wayne123");
		testUser = new User("Bruce", password, testRole);
		testCard = new CreditCard();
		testCard.setCardHolder("Clarke");
		testCard.setExpiryDate("03/24");
		testCard.setNumber("1234567898765");
		testCard.setUser(testUser);
		Mockito.when(creditCardRepository.findByNumber(testCard.getNumber())).thenReturn(testCard);

	}
	
	@Test
	public void findByNumberPositiveTest() {
		String number = "1234567898765";
		CreditCard foundCard = creditCardServiceImpl.findByCardNumber(testCard.getNumber());
		assertThat(foundCard.getNumber()).isEqualTo(number);
	}

	@Test
	public void findByNumberNegativeTest() {
		String number = "1234567898766";
		CreditCard foundCard = creditCardServiceImpl.findByCardNumber(testCard.getNumber());
		assertThat(foundCard.getNumber()).isEqualTo(number);
	}
	

}
