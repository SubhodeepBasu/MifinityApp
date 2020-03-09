package com.subho.mifinityapplication.service;

import com.subho.mifinityapplication.model.User;

/**
 * @author subasu This is the service interface for credit card functions. It
 *         has the business logic connecting the controllers to the repositories
 * @version 1.0
 */
public interface UserService {

	String findCurrentUsername();

	void userAutoLogin(String username, String password);

	boolean registerUser(User registrationDetails);

	User findByUserName(String userName);

}
