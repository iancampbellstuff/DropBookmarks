package com.udemy.dropbookmarks.db;

import java.util.List;

import org.hibernate.SessionFactory;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.User;

import io.dropwizard.hibernate.AbstractDAO;

/**
 * 
 * @author icampbell2
 */
public final class UserDAO extends AbstractDAO<User> {

	/**
	 * 
	 * @param sessionFactory
	 */
	public UserDAO(SessionFactory sessionFactory) {
		super(sessionFactory);
	}

	/**
	 * 
	 * @return
	 */
	public List<User> selectAll() {
		return list(namedQuery("com.udemy.dropbookmarks.core.User.selectAll"));
	}
	
	/**
	 * 
	 * @param username
	 * @param password
	 * @return
	 */
	public Optional<User> selectByCredentials(String username, String password) {
		return Optional.fromNullable(uniqueResult(
				namedQuery("com.udemy.dropbookmarks.core.User.selectByCredentials")
						.setParameter("username", username)
						.setParameter("password", password)
						
		));
	}
}
