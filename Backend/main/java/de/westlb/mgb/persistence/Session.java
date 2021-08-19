/*
 * Created on 03.06.2003
 */
package de.westlb.mgb.persistence;

import java.io.Serializable; 
import java.util.Collection;
import java.util.List;

import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.model.Entity;

/**
 * MGB session.
 * 
 * @author Manfred Boerner
 */
public interface Session {
	Transaction beginTransaction() throws PersistenceException;

    Entity create(Class<? extends Entity> type) throws PersistenceException;

	void delete(Entity data) throws PersistenceException;
	void delete(Class<? extends Entity> type, Serializable objectId) throws PersistenceException;
	void deleteAll(Collection<? extends Entity> collection) throws PersistenceException;

	void update(Entity data) throws PersistenceException;
	void save(Entity data) throws PersistenceException;
	void refresh(Entity data) throws PersistenceException;

	List<? extends Entity> select(Class<? extends Entity> type) throws PersistenceException;
	List<? extends Entity> select(Class<? extends Entity> type, String property, Object value) throws PersistenceException;
	List<? extends Entity> select(String query) throws PersistenceException;
	Entity select(Class<? extends Entity> type, Serializable objectId) throws PersistenceException;

	Query createQuery(String queryString) throws PersistenceException;

	void close() throws PersistenceException;
	void setCommitFlushMode();
	void setAutoFlushMode();
    void clear() throws PersistenceException;
    void flush() throws PersistenceException;
	void evict(Entity data) throws PersistenceException;
	
	public boolean isOpen();
	
	org.hibernate.Session getHibernateSession();
}
