package com.subho.mifinityapplication.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.subho.mifinityapplication.model.Role;

/**
 * @author subasu This is the repository for Roles and has methods for crud
 *         operations on the Roles table
 *
 */
public interface UserRoleRepository extends JpaRepository<Role, Long> {

	Role findByName(String name);

}
