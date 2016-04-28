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
	@Override
	public Optional<User> authenticate(BasicCredentials credentials) throws AuthenticationException {
		if (credentials.getPassword().equals(TestCredentials.getPassword())) {
			return Optional.of(new User());
			
		} else {
			return Optional.absent();
		}
	}
}
