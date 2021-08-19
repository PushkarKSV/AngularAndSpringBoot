package de.westlb.mgb.persistence.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.hibernate.FlushMode;
import org.hibernate.type.Type;
import org.hibernate.type.TypeFactory;
import org.hibernate.type.spi.TypeConfiguration;

import de.westlb.mgb.model.Entity;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;
import de.westlb.mgb.persistence.Transaction;

/**
 * Hibernate session implementation.
 * 
 * @author Manfred Boerner
 * @param <R>
 */
public class SessionImpl<R> extends Object implements Session, Serializable {
    private static final long serialVersionUID = 1161069254679529707L;
    private static final String MODEL_PACKAGE = "de.westlb.mgb.model.";
	private static final String IMPL_PACKAGE = "de.westlb.mgb.model.impl";
	public static final int BATCH_SIZE = 20;

    static Logger logger = Logger.getLogger(SessionImpl.class.getName());

	private StoreImpl store = null;
	private org.hibernate.Session session = null;
	private Transaction trx = null;
	
 
	public SessionImpl(StoreImpl store, org.hibernate.Session session, Properties properties) {
		super();
		this.store = store;
		this.session = session;
		System.out.println("Inside SessionImpl session ##: "+this.session);
	}

	@Override
    public boolean isOpen() {
		return session != null && session.isOpen();
	}
	/* 
	 */
	@Override
    public Transaction beginTransaction() throws PersistenceException {
		try {
			trx = new TransactionImpl(this, session.beginTransaction());
			logger.debug("Open  trx="+trx);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.BEGIN_TRX, e);
		}
		return trx;
	}

	protected String getImplementationName(Class<? extends Entity> type) throws PersistenceException {
		if (!Entity.class.isAssignableFrom(type))
			throw new PersistenceException(PersistenceException.ILLEGAL_TYPE, type);
			
		String typeName = type.getName();
		if (typeName.endsWith("Impl")) {
			return typeName;
		}
		
		int prefixLength = typeName.lastIndexOf('.');
		String prefix = typeName.substring(0, prefixLength + 1);
		if (!prefix.equals(MODEL_PACKAGE))
			throw new PersistenceException(PersistenceException.ILLEGAL_TYPE, type);
		return IMPL_PACKAGE + typeName.substring(prefixLength + 1) + "Impl";
	}

	@Override
    public Entity create(Class<? extends Entity> type) throws PersistenceException {
		if (trx == null)
			throw new PersistenceException(PersistenceException.MISSING_TRX);
		String className = getImplementationName(type);	// throws PersistenceException
		Entity entity = null;
		try {
			entity = (Entity)Class.forName(className).newInstance();
		} catch (Exception e) {							// ClassNotFound, IllegalAccess, Instantiation
			throw new PersistenceException(PersistenceException.CREATE_OBJ, className, e);
		}
		try {
			session.save(entity);
		} catch (Exception e) {							// Hibernate, SQL
			throw new PersistenceException(PersistenceException.SAVE_OBJ, entity, e);
		}
		return entity;
	}

    @Override
    public List<? extends Entity> select(Class<? extends Entity> type) throws PersistenceException {
		String className = getImplementationName(type);	// throws PersistenceException
		String query = "from obj in class " + className;
		try {
			//return session.find(query); //Hibernate2
			return session.createQuery(query).list();
		} catch (Exception e) {							// SQL, Hibernate
			throw new PersistenceException(PersistenceException.SELECT_OBJ, className, e);
		}
	}

	/* 
	 * Loads a persistent object by primary key
	 */
	@Override
    public Entity select(Class<? extends Entity> type, Serializable objectId) throws PersistenceException {
		Object result;
		if (objectId == null) {
			throw new PersistenceException(PersistenceException.SELECT_OBJ, type.getName(), "key", "null");			        				
		}
		
        try {
            result = session.get(type, objectId);
        } catch (Exception e) {
			throw new PersistenceException(PersistenceException.SELECT_OBJ, type.getName(), "key", objectId, e);			        	
        }
        
		if (result == null) {
			throw new PersistenceException(PersistenceException.SELECT_OBJ, type.getName(), "key", objectId);			
		}
		
		return (Entity)result;		
	}

	@SuppressWarnings("deprecation")
	@Override
    public List<? extends Entity> select(Class<? extends Entity> type, String property, Object value) throws PersistenceException {
		String className = getImplementationName(type);	// throws PersistenceException
		String queryString = "from obj in class " + className + " where obj." + property + " = ?";
		System.out.println("value : " + value + " type : "+ value.getClass().getName());
		try {
			org.hibernate.query.Query query = session.createQuery(queryString);
			query.setParameter(0,value, (Type) new TypeResolver(new TypeConfiguration(), new TypeFactory(new TypeConfiguration())).heuristicType(value.getClass().getName()));
			return query.list();
		} catch (Exception e) {							// SQL, Hibernate
			throw new PersistenceException(PersistenceException.SELECT_OBJ, className, property, value, e);
		}
	}

	@Override
    public List<? extends Entity> select(String query) throws PersistenceException {
		try {
			return session.createQuery(query).list();
		} catch (Exception e) {							// SQL, Hibernate
			throw new PersistenceException(PersistenceException.SELECT_OBJ, query, e);
		}
	}

	@Override
    public void delete(Class<? extends Entity> type, Serializable objectId) throws PersistenceException {
		delete(select(type, objectId));
	}

	@Override
    public void delete(Entity entity) throws PersistenceException {
		if (trx == null)
			throw new PersistenceException(PersistenceException.MISSING_TRX);
		try {
			session.delete(entity);	//? TODO: prevent multiple deletion of same entity in case of degree == 6
		} catch (Exception e) {							// SQL, Hibernate
			throw new PersistenceException(PersistenceException.DELETE_OBJ, entity, e);
		}
	}
	
	@Override
    public void deleteAll(Collection<? extends Entity> collection) throws PersistenceException  {
		Iterator<? extends Entity> iterator = collection.iterator();
		while (iterator.hasNext()) {
        	Entity element = iterator.next();
        	delete(element);
        }
	}

	@Override
    public void update(Entity data) throws PersistenceException {
       try {
            session.update(data);
        } catch (Exception e) {
			throw new PersistenceException(PersistenceException.SAVE_OBJ, data.getClass().getName(), e);			        	
        }
	}

	@Override
    public void save(Entity data) throws PersistenceException {
       try {
            session.save(data);
        } catch (Exception e) {
			throw new PersistenceException(PersistenceException.SAVE_OBJ, data.getClass().getName(), e);			        	
        }
	}

	@Override
    public void refresh(Entity data) throws PersistenceException {
       try {
            session.refresh(data);
        } catch (Exception e) {
			throw new PersistenceException(PersistenceException.SAVE_OBJ, data.getClass().getName(), e);			        	
        }
	}

	@Override
    public void close() throws PersistenceException {
		try {
			session.close();
		} catch (Exception e) {							// SQL, Hibernate
			throw new PersistenceException(PersistenceException.CLOSE_SESSION, e);
		}
		store.closeSession(this);
	}

	protected void endTransaction() {
		logger.debug("Close trx="+trx);
		trx = null;
	}
	
	@Override
    public Query createQuery(String queryString) throws PersistenceException {
		try {
			System.out.println("##createQuery session : "+session);
			return new QueryImpl(session.createQuery(queryString));
		} catch (Exception e) {
			e.printStackTrace();
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);			
		}
	}

	@Override
    public void setCommitFlushMode() {
		session.setFlushMode(FlushMode.COMMIT);
	}

	@Override
    public void setAutoFlushMode() {
		session.setFlushMode(FlushMode.AUTO);
	}
	
	@Override
    public void flush() throws PersistenceException {
		try {
			session.flush();
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.FLUSH_TRX, null, e);			
		}
	}

    @Override
    public void clear() throws PersistenceException {
        try {
            session.clear();
        } catch (Exception e) {
            throw new PersistenceException(PersistenceException.CLEAR_TRX, null, e);           
        }
    }

    @Override
    public void evict(Entity data) throws PersistenceException {
		try {
			session.evict(data);
		} catch (Exception e) {
			throw new PersistenceException(PersistenceException.COMMIT_TRX, null, e);			
		}
	}

	@Override
    public org.hibernate.Session getHibernateSession() {
		return session;
	}

}
