package com.verizon.config;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.verizon.dao.PersonRepository;
import com.verizon.domain.Person;

@Repository
public class DbChannel {

	@Autowired
	PersonRepository personRepository;

	public List<Person> findAll() {
		return personRepository.findAll();
	}

	public String addPerson(Person p) {
		// check for duplicate email
		Person dup = personRepository.findByEmail(p.getEmail());
		if (dup != null)
			return "email already exists";

		personRepository.save(p);
		return "person saved successfully";
	}

	public String deletePersonById(Long id) {
		personRepository.deleteById(id);
		return "deleted person with id" + id;
	}

	public Person getByEmailId(String email) {
		Person person = personRepository.findByEmail(email);
		
		return person;
	}

	@Transactional
	public void updatePass(String email, String newPassword) {
		personRepository.updatePassword(email, newPassword);
		
	}

}
