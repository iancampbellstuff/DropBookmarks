package com.udemy.dropbookmarks;

import io.dropwizard.Configuration;
import io.dropwizard.db.DataSourceFactory;

import com.fasterxml.jackson.annotation.JsonProperty;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.*;

public class DropBookmarksConfiguration extends Configuration {

	/**
	 * 
	 */
	@NotEmpty
	private String password;

	/**
	 * 
	 */
	@NotNull
	@Valid
	private DataSourceFactory dataSourceFactory = new DataSourceFactory();

	/**
	 * 
	 * @return
	 */
	@JsonProperty
	public String getPassword() {
		return password;
	}

	/**
	 * 
	 * @return
	 */
	@JsonProperty("database")
	public DataSourceFactory getDataSourceFactory() {
		return dataSourceFactory;
	}
}
