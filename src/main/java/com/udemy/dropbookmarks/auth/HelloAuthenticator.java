package com.udemy.dropbookmarks.auth;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.User;

import io.dropwizard.auth.AuthenticationException;
import io.dropwizard.auth.Authenticator;
import io.dropwizard.auth.basic.BasicCredentials;

/**
 * 
 * @author icampbell2
 */
public final class HelloAuthenticator implements Authenticator<BasicCredentials, User> {

	/**
	 * 
	 */
	private String password;
	
	/**
	 * 
	 * @param password
	 */
	public HelloAuthenticator(String password) {
		this.password = password;
	}
	
	/**
	 * 
	 */
	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		if (credentials.getPassword().equals(password)) {
			return Optional.of(new User());
			
		} else {
			return Optional.absent();
		}
	}
}
