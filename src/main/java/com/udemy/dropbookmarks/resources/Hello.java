package com.udemy.dropbookmarks.resources;

/**
 * 
 * @author icampbell2
 */
public enum Hello {
	
	/**
	 * 
	 */
	HELLO_WORLD("Hello world!", "/hello"),
	
	/**
	 * 
	 */
	HELLO_SECURED_WORLD("Hello secured world!", "/hello/secured");
	
	/**
	 * 
	 */
	private final String greeting;
	
	/**
	 * 
	 */
	private final String path;
	
	/**
	 * 
	 * @param greeting
	 * @param path
	 */
	Hello(String greeting, String path) {
		this.greeting = greeting;
		this.path = path;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getGreeting() {
		return this.greeting;
	}
	
	/**
	 * 
	 * @return
	 */
	public String getPath() {
		return this.path;
	}
}
