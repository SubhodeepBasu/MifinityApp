package com.subho.mifinityapplication.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import com.subho.mifinityapplication.model.CreditCard;

/**
 * @author subasu This is the repository for Credit Cards and has methods for
 *         crud operations on the Credit Cards table
 *
 */
public interface CreditCardRepository extends JpaRepository<CreditCard, Long> {

	@Query(value = "select * from credit_card cards WHERE cards.user_Id = :userName AND cards.number like %:number%", nativeQuery = true)
	public List<CreditCard> findCreditCardsForUser(@Param("userName") String userName, @Param("number") String number);

	@Modifying
	@Transactional
	@Query("UPDATE CreditCard c SET c.expiryDate = :expiryDate WHERE c.number = :number")
	public void updateCardExpiryDate(@Param("number") String cardNumber, @Param("expiryDate") String expiryDate);

	CreditCard findByNumber(String number);

	@Query("select cards from CreditCard cards where cards.number LIKE CONCAT( '%' ,:number, '%')")
	public List<CreditCard> findAllCards(String number);

}
