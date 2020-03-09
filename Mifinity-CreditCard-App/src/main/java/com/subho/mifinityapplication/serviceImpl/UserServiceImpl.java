package com.subho.mifinityapplication.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.subho.mifinityapplication.model.Role;
import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.repository.UserRepository;
import com.subho.mifinityapplication.repository.UserRoleRepository;
import com.subho.mifinityapplication.service.UserService;

/**
 * @author subasu This class overrides the UserService interface and implements
 *         the methods of the interface
 * @version 1.0
 */
@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDetailsService userDetailService;

	@Autowired
	private BCryptPasswordEncoder encoder;

	@Autowired
	private UserRoleRepository userRoleRepository;

	@Autowired
	private UserRepository userRepository;

	@Value("${user.role}")
	private String userRole;

	private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

	/**
	 * This method gets the username of the logged in user.
	 * 
	 * @return String
	 */
	@Override
	public String findCurrentUsername() {
		Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
		if (userDetails instanceof UserDetails) {
			return ((UserDetails) userDetails).getUsername();
		}
		return null;
	}

	/**
	 * @param username
	 * @param password This method creates a new token and grats requisite
	 *                 authorities for the logged in user
	 */
	@Override
	public void userAutoLogin(String username, String password) {
		UserDetails details = userDetailService.loadUserByUsername(username);
		UsernamePasswordAuthenticationToken token = new UsernamePasswordAuthenticationToken(details, password,
				details.getAuthorities());
		SecurityContextHolder.getContext().setAuthentication(token);
		logger.debug(String.format("%s logged in automatically successfully", username));
	}

	/**
	 * @param registrationDetails this method registers a new user and adds the role
	 *                            of a user
	 * @return boolean
	 */
	@Override
	public boolean registerUser(User registrationDetails) {
		try {
			registrationDetails.setPassword(encoder.encode(registrationDetails.getPassword()));
			Role userRole = userRoleRepository.findByName(this.userRole);
			registrationDetails.addToRoles(userRole);
			userRepository.save(registrationDetails);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}

	}

	/**
	 * @userName This method find a user based on username by invoking the
	 *           findByUserName method of the user repository
	 * @return User
	 */
	@Override
	public User findByUserName(String userName) {

		return userRepository.findByUserName(userName);
	}

}
