package com.verizon.util;

import java.util.regex.Pattern;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class PersonHelper {

	BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

	public String getHashPassword(String rawPassword) {
		return bCryptPasswordEncoder.encode(rawPassword);
	}

	public boolean isValidPassword(String encodedPassword, String rawPassword) {
		return bCryptPasswordEncoder.matches(rawPassword, encodedPassword);
	}

	public boolean isEmailValid(String email) {
		if (email == null)
			return false;

		String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." + "[a-zA-Z0-9_+&*-]+)*@" + "(?:[a-zA-Z0-9-]+\\.)+[a-z"
				+ "A-Z]{2,7}$";

		Pattern pat = Pattern.compile(emailRegex);

		return pat.matcher(email).matches();
	}

}
