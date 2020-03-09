package com.subho.mifinityapplication.model;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.NaturalId;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author subasu This is the model class for user and is mapped to the user
 *         table
 * @version 1.0
 */
@Entity
@Table(name = "user")
@Getter
@Setter
@NoArgsConstructor
public class User implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -614000034456968139L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@NaturalId
	private String userName;
	private String password;

	@Transient
	private String confirmPassword;

	@ManyToMany(fetch = FetchType.EAGER)
	private Set<Role> roles = new HashSet<Role>();

	public void addToRoles(Role role) {
		this.roles.add(role);
	}

	public User(@NotNull String userName, @NotNull String password, Role role) {
		super();
		this.userName = userName;
		this.password = password;
		this.addToRoles(role);
	}

}
