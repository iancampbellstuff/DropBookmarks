package com.udemy.dropbookmarks.resources;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.ForbiddenException;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.Bookmark;
import com.udemy.dropbookmarks.core.User;
import com.udemy.dropbookmarks.db.BookmarkDAO;

import io.dropwizard.auth.Auth;
import io.dropwizard.hibernate.UnitOfWork;
import io.dropwizard.jersey.params.LongParam;

@Path("/bookmarks") //https://localhost:8443/bookmarks
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public final class BookmarksResource {
	
	private BookmarkDAO dao;
	
	public BookmarksResource(BookmarkDAO dao) {
		this.dao = dao;
	}
	
	@GET
	@UnitOfWork
	public List<Bookmark> getBookmarks(@Auth User user) {
		return dao.findForUser(user.getId());
	}
	
	@GET
	@UnitOfWork
	@Path("/{id}") //https://localhost:8443/bookmarks/1
	public Optional<Bookmark> getBookmark(@Auth User user, @PathParam("id") LongParam id) {
		return findIfAuthorized(id.get(), user.getId());
	}
	
	@DELETE
	@UnitOfWork
	@Path("/{id}") //https://localhost:8443/bookmarks/1
	public Optional<Bookmark> delete(@Auth User user, @PathParam("id") LongParam id) {
		Optional<Bookmark> bookmark = findIfAuthorized(id.get(), user.getId());
		
		if (bookmark.isPresent()) {
			dao.delete(bookmark.get());
		}
		
		return bookmark;
	}
	
	@POST
	@UnitOfWork
	public Bookmark saveBookmark(@Auth User user, Bookmark bookmark) {
		bookmark.setUser(user);
		return dao.save(bookmark);
	}
	
	private Optional<Bookmark> findIfAuthorized(long bookmarkId, long userId) {
		Optional<Bookmark> result = dao.findById(bookmarkId);
		
		if (result.isPresent() && userId != result.get().getUser().getId()) {
			throw new ForbiddenException("You are not authorized to see this resource.");
		}
		
		return result;
	}
}
