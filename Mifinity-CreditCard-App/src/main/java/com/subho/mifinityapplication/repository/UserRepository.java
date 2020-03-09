package com.subho.mifinityapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subho.mifinityapplication.model.User;

/**
 * @author subasu This is the repository for User and has methods for crud
 *         operations on the User table
 */
public interface UserRepository extends JpaRepository<User, Long> {

	User findByUserName(String userName);

}
