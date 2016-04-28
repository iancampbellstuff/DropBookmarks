package com.udemy.dropbookmarks.auth;

/**
 * 
 * @author icampbell2
 */
public final class TestCredentials {
	
	/**
	 * 
	 */
	private static final String TEST_PASSWORD = "p@ssw0rd";
	
	/**
	 * 
	 */
	private TestCredentials() {
	}
	
	/**
	 * 
	 * @return
	 */
	public static String getPassword() {
		return TEST_PASSWORD;
	}
}
