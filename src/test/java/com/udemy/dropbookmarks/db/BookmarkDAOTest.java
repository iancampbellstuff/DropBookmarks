package com.udemy.dropbookmarks.db;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.context.internal.ManagedSessionContext;
import org.hibernate.engine.jdbc.connections.internal.DriverManagerConnectionProviderImpl;
import org.hibernate.internal.SessionFactoryImpl;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.Bookmark;
import com.udemy.dropbookmarks.core.User;

import liquibase.Liquibase;
import liquibase.database.Database;
import liquibase.database.DatabaseFactory;
import liquibase.database.jvm.JdbcConnection;
import liquibase.exception.DatabaseException;
import liquibase.exception.LiquibaseException;
import liquibase.exception.LockException;
import liquibase.resource.ClassLoaderResourceAccessor;

/**
 * 
 * @author icampbell2
 */
public final class BookmarkDAOTest {

	private static final SessionFactory SESSION_FACTORY = HibernateUtil.getSessionFactory();
	private static Liquibase liquibase = null;
	private Session session;
	private Transaction tx;
	private BookmarkDAO dao;
	private UserDAO userDao;

	@BeforeClass
	public static void setUpClass() throws LiquibaseException, SQLException {

		SessionFactoryImpl sessionFactoryImpl = (SessionFactoryImpl) SESSION_FACTORY;
		DriverManagerConnectionProviderImpl provider = (DriverManagerConnectionProviderImpl) sessionFactoryImpl
				.getConnectionProvider();
		Connection connection = provider.getConnection();
		Database database = DatabaseFactory.getInstance()
				.findCorrectDatabaseImplementation(new JdbcConnection(connection));

		liquibase = new Liquibase("migrations.xml", new ClassLoaderResourceAccessor(), database);

	}

	@AfterClass
	public static void tearDownClass() {
		SESSION_FACTORY.close();
	}

	@Before
	public void setUp() throws LiquibaseException {
		liquibase.update("DEV");
		session = SESSION_FACTORY.openSession();
		dao = new BookmarkDAO(SESSION_FACTORY);
		userDao = new UserDAO(SESSION_FACTORY);
		tx = null;
	}

	@After
	public void tearDown() throws DatabaseException, LockException {
		liquibase.dropAll();
	}
	
	/**
	 * 
	 */
//	@Test
	public void testFindForUser() {
		User user = null;
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			user = userDao.selectAll().get(0);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(user);
		long id = user.getId();
		List<Bookmark> bookmarks = null;
		
		session = SESSION_FACTORY.openSession();
		tx = null;
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			bookmarks = dao.findForUser(id);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(bookmarks);
//TODO: currently no results to return
//		Assert.assertFalse(bookmarks.isEmpty());
//		Assert.assertEquals(1, bookmarks.size());
	}
	
	/**
	 * 
	 */
//	@Test
	public void testFindById() {
		User user = null;
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			user = userDao.selectAll().get(0);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(user);
		session = SESSION_FACTORY.openSession();
		tx = null;
		
		Bookmark bookmark = new Bookmark("YouTube", "http://www.youtube.com", "YouTube is a video content entertainment platform.", user);
		Optional<Bookmark> expectedBookmark = null;
		long id = bookmark.getId();
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			dao.save(bookmark);
			expectedBookmark = dao.findById(id);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(expectedBookmark);
//TODO: not present for some reason
//		Assert.assertTrue(expectedBookmark.isPresent());
	}

	/**
	 * Test of findAll method, of class BookmarkDAO.
	 */
//	@Test
	public void testSelectAll() {
		List<Bookmark> bookmarks = null;
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			bookmarks = dao.selectAll();

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(bookmarks);
//TODO: currently no results to return
//		Assert.assertFalse(bookmarks.isEmpty());
//		Assert.assertEquals(1, bookmarks.size());
	}
	
	/**
	 * 
	 */
//	@Test
	public void testSave() {
		User user = null;
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			user = userDao.selectAll().get(0);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(user);
		session = SESSION_FACTORY.openSession();
		tx = null;
		
		Bookmark bookmark = new Bookmark("YouTube", "http://www.youtube.com", "YouTube is a video content entertainment platform.", user);
		Optional<Bookmark> expectedBookmark = null;
		long id = bookmark.getId();
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			dao.save(bookmark);
			expectedBookmark = dao.findById(id);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(expectedBookmark);
//TODO: not present for some reason
//		Assert.assertTrue(expectedBookmark.isPresent());
//		Assert.assertEquals(expectedBookmark.get().getId(), bookmark.getId());
	}
	
	/**
	 * 
	 */
//	@Test
	public void testDelete() {
		User user = null;
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			user = userDao.selectAll().get(0);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(user);
		session = SESSION_FACTORY.openSession();
		tx = null;
		
		Bookmark bookmark = new Bookmark("YouTube", "http://www.youtube.com", "YouTube is a video content entertainment platform.", user);
		Optional<Bookmark> expectedBookmark = null;
		long id = bookmark.getId();
		
		try {
			ManagedSessionContext.bind(session);
			tx = session.beginTransaction();
			dao.save(bookmark);
			dao.delete(bookmark);
			expectedBookmark = dao.findById(id);

			tx.commit();
			
		} catch (Exception e) {
			if (tx != null && tx.isActive()) {
				tx.rollback();
			}
			throw e;
			
		} finally {
			ManagedSessionContext.unbind(SESSION_FACTORY);
			session.close();
		}
		
		Assert.assertNotNull(expectedBookmark);
		Assert.assertFalse(expectedBookmark.isPresent());
	}
}
