/*
 * The MIT License
 *
 * Copyright 2015 Dmitry Noranovich.
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package com.udemy.dropbookmarks.db;

import com.google.common.base.Optional;
import com.udemy.dropbookmarks.core.Bookmark;
import io.dropwizard.hibernate.AbstractDAO;
import java.util.List;
import org.hibernate.SessionFactory;

/**
 * A data access object to manipulate bookmarks.
 *
 * @author Dmitry Noranovich
 */
public class BookmarkDAO extends AbstractDAO<Bookmark> {

    public BookmarkDAO(SessionFactory sessionFactory) {
        super(sessionFactory);
    }

    /**
     * Find all bookmarks for the user with specified id.
     *
     * @param id user id
     * @return list of bookmarks for the user with specified id.
     */
    public List<Bookmark> findForUser(long id) {
        return list(namedQuery("com.udemy.dropbookmarks.core.Bookmark.findForUser")
        		.setParameter("id", id));
    }

    /**
     * Finds a bookmark by its id.
     *
     * @param id id of a bookmark
     * @return a bookmark with specified id
     */
    public Optional<Bookmark> findById(long id) {
        return Optional.fromNullable(get(id));
    }

    /**
     * Create or Update a bookmark.
     *
     * @param bookmark a bookmark to be saved
     * @return the saved bookmark with all auto-generated fields filled.
     */
    public Bookmark save(Bookmark bookmark) {
        return persist(bookmark);
    }

    /**
     * Removes a bookmark from the database.
     *
     * @param bookmark a bookmark to be removed.
     */
    public void delete(Bookmark bookmark) {
        namedQuery("com.udemy.dropbookmarks.core.Bookmark.remove")
        		.setParameter("id", bookmark.getId())
        		.executeUpdate();
    }
}
