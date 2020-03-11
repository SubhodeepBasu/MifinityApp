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

import com.subho.mifinityapplication.model.Role;
import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.repository.UserRepository;
import com.subho.mifinityapplication.repository.UserRoleRepository;
import com.subho.mifinityapplication.serviceImpl.UserServiceImpl;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Mock
	private UserRepository userRepository;

	@Mock
	private BCryptPasswordEncoder encoder;

	@Mock
	private UserRoleRepository userRoleRepository;

	@Value("${user.role}")
	private String userRole;
	
	Role testRole;
	
	User testUser;

	@Before
	public void setUp() {

		testRole = new Role(userRole);
		String password = encoder.encode("Wayne123");
		testUser = new User("Bruce", password, testRole);
		Mockito.when(userRepository.findByUserName(testUser.getUserName())).thenReturn(testUser);
	}
	
	@Test
	public void RegisterUserPositiveTest() {
		boolean result = userServiceImpl.registerUser(testUser);
		assertThat(result).isEqualTo(true);
	}
	
	@Test
	public void RegisterUserNegativeTest() {
		boolean result = userServiceImpl.registerUser(testUser);
		assertThat(result).isEqualTo(false);
	}

	@Test
	public void findByUsernamePositiveTest() {
		User foundUser = userServiceImpl.findByUserName(testUser.getUserName());
		assertThat(foundUser.getUserName()).isEqualTo(testUser.getUserName());
	}

	@Test
	public void findByUsernameNegativeTest() {
		String name = "Clarke";
		User foundUser = userServiceImpl.findByUserName(testUser.getUserName());
		assertThat(foundUser.getUserName()).isEqualTo(name);
	}

}
