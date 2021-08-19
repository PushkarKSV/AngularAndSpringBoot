package de.westlb.mgb.persistence.hibernate;

import java.io.Serializable;
import java.util.GregorianCalendar;
import java.util.Iterator;

import org.hibernate.CallbackException;
import org.hibernate.EntityMode;
import org.hibernate.Interceptor;
import org.hibernate.Transaction;
import org.hibernate.type.Type;

import de.westlb.mgb.model.impl.EntityImpl;
import de.westlb.mgb.model.impl.MandantImpl;

/**
 * @author D055625
 *
 * @see org.hibernate.Interceptor
 */
public class PersistencyInterceptor implements Interceptor, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8189056603931883353L;

	private String updateCreateUser = "";

	private MandantImpl mandant = null;

	public PersistencyInterceptor(String updateCreateUser) {
		this.updateCreateUser = updateCreateUser;
	}

	public PersistencyInterceptor(
		String updateCreateUser,
		MandantImpl mandant) {
		this.updateCreateUser = updateCreateUser;
		this.mandant = mandant;
	}

	/**
	 * @see org.hibernate.Interceptor#findDirty(Object, Serializable, Object[], Object[], String[], Type[])
	 */
	@Override
    public int[] findDirty(
		Object entity,
		Serializable id,
		Object[] currentState,
		Object[] previousState,
		String[] propertyNames,
		Type[] types) {
		return null;
	}

	/**
	 * @see org.hibernate.Interceptor#instantiate(Class, Serializable)
	 */
	@SuppressWarnings("rawtypes")
    public Object instantiate(Class arg0, Serializable arg1)
		throws CallbackException {
		return null;
	}

	/**
	 * @see org.hibernate.Interceptor#onDelete(Object, Serializable, Object[], String[], Type[])
	 */
	@Override
    public void onDelete(
		Object entity,
		Serializable id,
		Object[] currentState,
		String[] propertyNames,
		Type[] types)
		throws CallbackException {
	}

	/**
	 * Add audit information to an EntityImpl object before it is updated in the database.
	 * The EntityImpl member changedDate is set to the current timestamp.
	 * The EntityImpl member changedBy to the user that has been set with the constructor.
	 * 
	 * @see org.hibernate.Interceptor#onFlushDirty(Object, Serializable, Object[], Object[], String[], Type[])
	 */
	@Override
    public boolean onFlushDirty(
		Object entity,
		Serializable id,
		Object[] currentState,
		Object[] previousState,
		String[] propertyNames,
		Type[] types)
		throws CallbackException {

		boolean result = false;

		if (entity instanceof EntityImpl) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("changedDate".equals(propertyNames[i])) {
					currentState[i] = new GregorianCalendar();
					result = true;
				}
				if ("changedBy".equals(propertyNames[i])) {
					currentState[i] = updateCreateUser;
					result = true;
				}
			}
		}
		return result;
	}

	/**
	 * @see org.hibernate.Interceptor#onLoad(Object, Serializable, Object[], String[], Type[])
	 */
	@Override
    public boolean onLoad(
		Object entity,
		Serializable id,
		Object[] currentState,
		String[] propertyNames,
		Type[] types)
		throws CallbackException {
		return false;
	}

	/**
	 * Add audit information to an EntityImpl object before it is inserted into the database.
	 * The EntityImpl member creationDate is set to the current timestamp.
	 * The EntityImpl member createdBy is set to the user that has been set with the constructor.
	 * If the EntityImpl member mandant is empty, is set to the MandantImpl object, if it has been set with the constructor.
	 * Otherwise a CallbackException is thrown.
	 * 
	 * @see org.hibernate.Interceptor#onSave(Object, Serializable, Object[], String[], Type[])
	 */
	@Override
    public boolean onSave(
		Object entity,
		Serializable id,
		Object[] currentState,
		String[] propertyNames,
		Type[] types)
		throws CallbackException {

		boolean result = false;

		if (entity instanceof EntityImpl) {
			for (int i = 0; i < propertyNames.length; i++) {
				if ("creationDate".equals(propertyNames[i])) {
					currentState[i] = new GregorianCalendar();
					result = true;
				}
				if ("createdBy".equals(propertyNames[i])) {
					currentState[i] = updateCreateUser;
					result = true;
				}
				if ("mandant".equals(propertyNames[i]) && currentState[i] == null) {
					if (mandant != null) {
						currentState[i] = mandant;
						result = true;
					} else {
						throw new CallbackException("To save an Object that extents an EntityImpl the mandant field is mandatory.");
					}
				}
			}
		}
		return result;
	}

	/**
	 * @see org.hibernate.Interceptor#postFlush(Iterator)
	 */
	@Override
    @SuppressWarnings("rawtypes")
    public void postFlush(Iterator arg0) throws CallbackException {
	}

	/**
	 * @see org.hibernate.Interceptor#preFlush(Iterator)
	 */
	@Override
    @SuppressWarnings("rawtypes")
    public void preFlush(Iterator arg0) throws CallbackException {
	}

	/**
	 * @see org.hibernate.Interceptor#afterTransactionBegin(org.hibernate.Transaction)
	 */
	@Override
    public void afterTransactionBegin(Transaction arg0) {
	}

	/**
	 * @see org.hibernate.Interceptor#afterTransactionCompletion(org.hibernate.Transaction)
	 */
	@Override
    public void afterTransactionCompletion(Transaction arg0) {
	}

	/**
	 * @see org.hibernate.Interceptor#beforeTransactionCompletion(org.hibernate.Transaction)
	 */
	@Override
    public void beforeTransactionCompletion(Transaction arg0) {
	}

	/**
	 * @see org.hibernate.Interceptor#getEntity(java.lang.String, java.io.Serializable)
	 */
	@Override
    public Object getEntity(String arg0, Serializable arg1)
			throws CallbackException {
		return null;
	}
	/**
	 * @see org.hibernate.Interceptor#getEntityName(java.lang.Object)
	 */
	@Override
    public String getEntityName(Object arg0) throws CallbackException {
		return null;
	}

	/**
	 * @see org.hibernate.Interceptor#instantiate(java.lang.String, org.hibernate.EntityMode, java.io.Serializable)
	 */
	@Override
    public Object instantiate(String arg0, EntityMode arg1, Serializable arg2)
			throws CallbackException {
		return null;
	}
	
	/**
	 * @see org.hibernate.Interceptor#isTransient(java.lang.Object)
	 */
	@Override
    public Boolean isTransient(Object arg0) {
		return Boolean.FALSE;
	}

    /**
     * @see org.hibernate.Interceptor#onCollectionRecreate(java.lang.Object, java.io.Serializable)
     */
    @Override
    public void onCollectionRecreate(Object arg0, Serializable arg1) throws CallbackException {
    }

    /**
     * @see org.hibernate.Interceptor#onCollectionRemove(java.lang.Object, java.io.Serializable)
     */
    @Override
    public void onCollectionRemove(Object arg0, Serializable arg1) throws CallbackException {
    }

    /**
     * @see org.hibernate.Interceptor#onCollectionUpdate(java.lang.Object, java.io.Serializable)
     */
    @Override
    public void onCollectionUpdate(Object arg0, Serializable arg1) throws CallbackException {
    }

    /**
     * @see org.hibernate.Interceptor#onPrepareStatement(java.lang.String)
     */
    @Override
    public String onPrepareStatement(String arg0) {
        return arg0;
    }
}
