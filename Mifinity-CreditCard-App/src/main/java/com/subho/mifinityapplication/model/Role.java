package com.subho.mifinityapplication.model;

import java.io.Serializable;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author subasu This is the model class for Role and is mapped to the role
 *         table
 * @version 1.0
 */
@Entity
@Table(name = "role")
@Data
public class Role implements Serializable {

	private static final long serialVersionUID = 6479931385419962896L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String name;
	@ManyToMany(mappedBy = "roles")
	private Set<User> users;

	public Role(String name) {
		super();
		this.name = name;
	}

}
