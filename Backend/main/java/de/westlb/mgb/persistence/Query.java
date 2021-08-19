package de.westlb.mgb.persistence;

import java.util.Collection; 
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.hibernate.HibernateException;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public interface Query {
	
	/**
	 * Sets the max value for the result set
	 *
	 * @return the query string
	 */
	public Query setMaxResults(int max);
	/**
	 * Get the query string.
	 *
	 * @return the query string
	 */
	public String getQueryString();
	/**
	 * Return the names of all named parameters of the query.
	 * @return the parameter names, in no particular order
	 */
	public Set<String> getNamedParameters() throws PersistenceException;
	/**
	 * Return the query results as an <tt>Iterator</tt>. If the query
	 * contains multiple results pre row, the results are returned in
	 * an instance of <tt>Object[]</tt>.<br>
	 * <br>
	 * Entities returned as results are initialized on demand. The first
	 * SQL query returns identifiers only.<br>
	 *
	 * @return the result iterator
	 * @throws PersistenceException
	 */
	@SuppressWarnings("rawtypes")
    public Iterator iterate() throws PersistenceException;
	/**
	 * Return the query results as a <tt>List</tt>. If the query contains
	 * multiple results pre row, the results are returned in an instance
	 * of <tt>Object[]</tt>.
	 *
	 * @return the result list
	 * @throws HibernateException
	 */
	@SuppressWarnings("rawtypes")
    public List list() throws PersistenceException;

	/**
	 * Bind a value to a named query parameter, guessing the Hibernate
	 * type from the class of the given object.
	 * @param name the name of the parameter
	 * @param val the non-null parameter value
	 * @throws net.sf.hibernate.HibernateException if no type could be determined
	 */
	public Query setParameter(String name, Object val)
		throws PersistenceException;

	/**
	 * Bind multiple values to a named query parameter, guessing the Hibernate type from the
	 * class of the first object in the collection. This is useful for binding a list of values
	 * to an expression such as <tt>foo.bar in (:value_list)</tt>.
	 * @param name the name of the parameter
	 * @param vals a collection of values to list
	 */
	@SuppressWarnings("rawtypes")
    public Query setParameterList(String name, Collection vals)
		throws PersistenceException;

	/**
	 * Bind multiple values to a named query parameter, guessing the Hibernate type from the
	 * class of the first object in the array. This is useful for binding a list of values
	 * to an expression such as <tt>foo.bar in (:value_list)</tt>.
	 * @param name the name of the parameter
	 * @param vals a collection of values to list
	 */
	public Query setParameterList(String name, Object[] vals)
		throws PersistenceException;

	/**
	 * Bind the property values of the given bean to named parameters of the query,
	 * matching property names with parameter names and mapping property types to
	 * Hibernate types using hueristics.
	 * @param bean any JavaBean or POJO
	 */
	public Query setProperties(Object bean) throws PersistenceException;
	
	/**
	 * Executes bulk SQL-Style update on database
	 * @return number of records updated
	 */	
	public int executeUpdate() throws PersistenceException;
	
	public Object uniqueResult() throws PersistenceException;

	
}

