/*
 * Created on 03.06.2003
 */
package de.westlb.mgb.persistence;

/**
 * Mgb transaction.
 * 
 * @author Manfred Boerner
 */
public interface Transaction {
	void commit() throws PersistenceException;
	void rollback() throws PersistenceException;
	boolean wasCommitted();
	boolean wasRolledBack();
}
