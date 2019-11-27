package com.verizon.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.verizon.dto.ChangePasswordDTO;
import com.verizon.dto.PersonDetailsDTO;
import com.verizon.service.PersonService;

import io.swagger.annotations.Api;

@Api(value = "person")
@RestController
@RequestMapping("/person")
public class PersonController {

	@Autowired
	PersonService personService;

	private static final Logger log = LoggerFactory.getLogger(PersonController.class);

	@CrossOrigin
	@RequestMapping(value = "/create", method = RequestMethod.POST)
	public ResponseEntity<String> create(@RequestBody PersonDetailsDTO personDetailsDTO) throws InterruptedException {
		log.info("create method called :");
		return ResponseEntity.ok(personService.create(personDetailsDTO));
	}

	/*@CrossOrigin
	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public ResponseEntity<String> update(@RequestBody PersonDetailsDTO personDetailsDTO) throws InterruptedException {
		log.info("update method called :");
		return ResponseEntity.ok(personService.create(personDetailsDTO));

	}*/

	@CrossOrigin
	@RequestMapping(value = "/delete/{id}", method = RequestMethod.DELETE)
	public ResponseEntity<String> deleteById(@PathVariable("id") Long id) throws InterruptedException {
		log.info("delete method called :");
		return ResponseEntity.ok(personService.delete(id));

	}

	@CrossOrigin
	@RequestMapping(value = "/getByEmail/{email}", method = RequestMethod.GET)
	public ResponseEntity<Object> getByEmail(@PathVariable("email") String email) throws InterruptedException {
		log.info("getByEmail method called :email id { }", email);
		String error;
		try {
			PersonDetailsDTO pdto = personService.getByEmail(email);
			return ResponseEntity.ok(pdto);
		} catch (RuntimeException e) {
			error = e.getMessage();
			log.error(e.getMessage());
		}
		return ResponseEntity.status(400).body(error);

	}

	@CrossOrigin
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public ResponseEntity<String> login(@RequestParam String email, @RequestParam String password)
			throws InterruptedException {
		log.info("login method called :email id { }", email);
		return ResponseEntity.ok(personService.login(email, password));

	}

	@CrossOrigin
	@RequestMapping(value = "/changePassword", method = RequestMethod.POST)
	public ResponseEntity<String> changePassword(@RequestBody ChangePasswordDTO changePasswordDTO)
			throws InterruptedException {
		log.info("changePassword method called :");
		return ResponseEntity.ok(personService.changePassword(changePasswordDTO));
	}
}
