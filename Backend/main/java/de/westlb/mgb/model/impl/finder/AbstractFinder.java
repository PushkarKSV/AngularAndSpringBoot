/*
 * Created on Apr 2, 2004
 *
 * To change the template for this generated file go to
 * Window&gt;Preferences&gt;Java&gt;Code Generation&gt;Code and Comments
 */
package de.westlb.mgb.model.impl.finder;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map.Entry;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.log4j.Logger;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import de.westlb.mgb.model.Entity;
import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Query;
import de.westlb.mgb.persistence.Session;

/**
 * @author WSY4148
 *
 */
public class AbstractFinder {

	protected static Logger logger = Logger.getLogger(AbstractFinder.class);

	protected Session sess;

	protected AbstractFinder(Session sess) {
		
		this.sess = sess;
	}

	protected javax.persistence.Query buildQuery(String table, HashMap<String, Object> columnValues, ArrayList<String> clauseTokens) throws PersistenceException {
		return buildQuery(table, columnValues, clauseTokens, "");
	}

	 @PersistenceContext
	    private EntityManager entityManager;

	protected javax.persistence.Query buildQuery(String table, HashMap<String, Object> columnValues, ArrayList<String> clauseTokens, String endClause) throws PersistenceException {
		javax.persistence.Query query = null;
		try {
			
		query = entityManager.createQuery(table + wereClauseFromTokenList(clauseTokens) + " " + endClause);
		} catch (Exception e0 ) {
			e0.printStackTrace();
		}
		
		Iterator<Entry<String, Object>> i = columnValues.entrySet().iterator();
		
		String queryString = query.unwrap(org.hibernate.Query.class).getQueryString();


		while (i.hasNext()) {
			Entry<String, Object> entry = i.next();
			query.setParameter(entry.getKey(), entry.getValue());

			
			if (logger.isDebugEnabled()) {
				String value = entry.getValue().toString();
				if (entry.getValue() instanceof Entity) {
					value = ((Entity)entry.getValue()).getId().toString();
					
				} else if (entry.getValue() instanceof Calendar) {
					value = ((Calendar)entry.getValue()).getTime().toString();
					
				}

				queryString = queryString.replaceAll(":"+entry.getKey(), "'"+value+"'");
				
			}
		}
		logger.debug("HQL: " + queryString);
		System.out.println("Querystring outside## "+queryString);
		return query;
	}

	protected String wereClauseFromTokenList(List<String> clauseTokens) {
		StringBuffer whereClause = new StringBuffer();
		for (int i = 0; i < clauseTokens.size(); i++) {
			if (i == 0) {
				whereClause.append(" where ");
			} else {
				whereClause.append(" and ");
			}
			whereClause.append(clauseTokens.get(i));
		}
		
		return whereClause.toString();
	}


}
