package com.udemy.dropbookmarks.resources;

/**
 * 
 * @author icampbell2
 */
public enum HelloEnum {
	
	/**
	 * 
	 */
	HELLO_WORLD("Hello world!");
	
	/**
	 * 
	 */
	private final String value;
	
	/**
	 * 
	 * @param value
	 */
	HelloEnum(String value) {
		this.value = value;
	}
	
	/**
	 * 
	 */
	@Override
	public String toString() {
		return this.value;
	}
}
