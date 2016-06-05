package com.udemy.dropbookmarks.resources;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.udemy.dropbookmarks.core.User;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;

/**
 * 
 * @author icampbell2
 */
@Path("/hello")
public final class HelloResource {
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String getGreeting() {
		return Hello.HELLO_WORLD.getGreeting();
	}
	
	/**
	 * 
	 * @return
	 */
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Path("/secured") // sub-resource path as /hello/secured
	@UnitOfWork
	public String getSecuredGreeting(@Auth User user) {
		return Hello.HELLO_SECURED_WORLD.getGreeting();
	}
}
