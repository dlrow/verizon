package com.verizon.dao;

import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.verizon.domain.Person;

@Configuration
public interface PersonRepository extends JpaRepository<Person, Long> {

	Person findByEmail(String email);
	
	@Modifying
	@Query("update Person per set per.password = ?2 where per.email = ?1")
	int updatePassword(String email, String pass);

}
