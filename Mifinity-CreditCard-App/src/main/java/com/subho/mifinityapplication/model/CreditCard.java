package com.subho.mifinityapplication.model;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import lombok.Data;

/**
 * @author subasu This is the model class for credit card and is mapped to the
 *         credit_card table
 * @version 1.0
 */
@Entity
@Table(name = "credit_card")
@Data
public class CreditCard implements Serializable {

	private static final long serialVersionUID = -135838147220543574L;
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	private String cardHolder;
	@NaturalId
	private String number;
	private String expiryDate;

	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "user_Id", referencedColumnName = "userName", nullable = false)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;

}
