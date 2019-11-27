package com.verizon.service;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.verizon.config.DbChannel;
import com.verizon.domain.Person;
import com.verizon.dto.ChangePasswordDTO;
import com.verizon.dto.PersonDetailsDTO;
import com.verizon.util.PersonHelper;

@Service
public class PersonService {

	@Autowired
	DbChannel dbChannel;

	@Autowired
	PersonHelper personHelper;

	public String create(PersonDetailsDTO personDetailsDTO) {

		// check null fields
		if (!personHelper.isEmailValid(personDetailsDTO.getEmail()))
			return "invalid email";
		if (personDetailsDTO.getFirstName() == null)
			return "first name cannot be blank";
		if (personDetailsDTO.getPassword() == null)
			return "password cannot be blank";

		Person p = new Person(personDetailsDTO.getEmail(), personDetailsDTO.getFirstName(),
				personDetailsDTO.getLastName(), personHelper.getHashPassword(personDetailsDTO.getPassword()));

		return dbChannel.addPerson(p);
	}

	public String delete(Long id) {
		if (id == null)
			return "invalid id";
		return dbChannel.deletePersonById(id);
	}

	public PersonDetailsDTO getByEmail(String email) throws RuntimeException {
		if (!personHelper.isEmailValid(email))
			throw new RuntimeException("invalid email");
		Person person = dbChannel.getByEmailId(email);
		if (person == null)
			throw new RuntimeException("person cannot be found");
		PersonDetailsDTO pdto = new PersonDetailsDTO(person.getEmail(), person.getFirstName(), person.getLastName());
		return pdto;
	}

	public String login(String email, String password) {
		if (!personHelper.isEmailValid(email))
			return "invalid email";
		Person person = dbChannel.getByEmailId(email);
		if (person == null)
			return "person not found";
		if (personHelper.isValidPassword(person.getPassword(), password))
			return "Welcome " + person.getFirstName() + " " + person.getLastName();
		return "Access denied";

	}

	@Transactional
	public String changePassword(ChangePasswordDTO changePasswordDTO) {
		if (!personHelper.isEmailValid(changePasswordDTO.getEmail()))
			return "invalid email";
		if (!changePasswordDTO.getNewPassword().equals(changePasswordDTO.getConfirmNewPassword()))
			return "new password and confirm new password doesn't match";

		
		Person person = dbChannel.getByEmailId(changePasswordDTO.getEmail());
		if (person == null)
			return "person not found";
		if (personHelper.isValidPassword(person.getPassword(), changePasswordDTO.getCurrPassword())) {
			dbChannel.updatePass(person.getEmail(), personHelper.getHashPassword(changePasswordDTO.getNewPassword()));
			return "password changed successfully";
		}else
			return "old password not correct";

		
	}

}
