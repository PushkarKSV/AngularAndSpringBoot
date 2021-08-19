package de.westlb.mgb.persistence;

import de.westlb.mgb.model.impl.MandantImpl;


/**
 * MGB store implementation based on Hibernate
 * 
 * @author Manfred Boerner
 */
public interface Store {

	/**
	 * 
	 */
	public void close() throws PersistenceException;
	public Session openSession() throws PersistenceException;
	public Session openSession(MandantImpl mandant, String ntid) throws PersistenceException;
    public Session openBatchSession() throws PersistenceException;
    public Session openBatchSession(MandantImpl mandant, String ntid) throws PersistenceException;
	public void clearCache() throws PersistenceException;
    public void clearBatchCache() throws PersistenceException;
	
}
