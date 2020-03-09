package com.subho.mifinityapplication.serviceImpl;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.subho.mifinityapplication.model.User;
import com.subho.mifinityapplication.repository.UserRepository;

/**
 * @author subasu This class overrides the UserDetailsService interface and
 *         implements the loadUserByUsername method of the interface
 * @version 1.0
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {

	@Autowired
	private UserRepository userRepo;

	/**
	 * @param username This method gets the user details of a user based on username
	 *                 by calling the loadUserByUsername method of the user
	 *                 repository
	 */
	@Override
	@Transactional(readOnly = true)
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepo.findByUserName(username);
		Set<GrantedAuthority> grantedAuthorities = new HashSet<>();
		user.getRoles().forEach(role -> grantedAuthorities.add(new SimpleGrantedAuthority(role.getName())));
		System.out.println("TESTING..." + grantedAuthorities);
		return new org.springframework.security.core.userdetails.User(user.getUserName(), user.getPassword(),
				grantedAuthorities);

	}

}
