package de.westlb.mgb.persistence.hibernate;

import java.io.Serializable;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
@SuppressWarnings("rawtypes")
public class QueryImpl implements Query, Serializable {

    private static final long serialVersionUID = -5261812301184383423L;
    private org.hibernate.query.Query query;

	public QueryImpl(org.hibernate.query.Query aQuery) {
		this.query = aQuery;
	}
	
	@Override
    public Query setMaxResults(int max) {
		query = query.setMaxResults(max);
		return this;
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#getNamedParameters()
	 */
	@Override
    public java.util.Set<String> getNamedParameters() throws PersistenceException {
		try {
			//return query.getNamedParameters();
			 java.util.Set<String> namedParameters = query.getParameterMetadata().getNamedParameterNames();
			 return namedParameters;
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#getQueryString()
	 */
	@Override
    public String getQueryString() {
//		return query.getQueryString();
		return ((Object) query).toString();
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#iterate()
	 */
	@Override
    public Iterator iterate() throws PersistenceException {
		try {
			return query.iterate();
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	@Override
    public Object uniqueResult() throws PersistenceException {
		try {
			return query.uniqueResult();
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#list()
	 */
	@Override
    public List list() throws PersistenceException {
		try {
			return query.list();
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#setParameter(String, Object)
	 */
	@Override
    public Query setParameter(String name, Object val)
		throws PersistenceException {
		try {
			query.setParameter(name, val);
			return this;
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#setParameterList(String, Collection)
	 */
	@Override
    public Query setParameterList(String name, Collection vals)
		throws PersistenceException {
		try {
			query.setParameterList(name, vals);
			return this;
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#setParameterList(String, Object[])
	 */
	@Override
    public Query setParameterList(String name, Object[] vals)
		throws PersistenceException {
		try {
			query.setParameterList(name, vals);
			return this;
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/**
	 * @see de.westlb.mgb.persistence.Query#setProperties(Object)
	 */
	@Override
    public Query setProperties(Object bean) throws PersistenceException {
		try {
			query.setProperties(bean);
			return this;
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.CREATE_QRY, null, e);
		}
	}

	/* (non-Javadoc)
	 * @see de.westlb.mgb.persistence.Query#executeUpdate()
	 */
	@Override
    public int executeUpdate() throws PersistenceException {
		try {
			return query.executeUpdate();
		} catch (HibernateException e) {
			throw new PersistenceException(PersistenceException.EXEC_UPDATE, null, e);
		}
	}
}
