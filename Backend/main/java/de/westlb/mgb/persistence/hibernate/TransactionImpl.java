package de.westlb.mgb.persistence.hibernate;

import java.io.Serializable;

import de.westlb.mgb.persistence.PersistenceException;
import de.westlb.mgb.persistence.Transaction;

/**
 * Bizkaia Hibernate transaction implementation.
 * 
 * @author Oliver Stuhr
 */
public class TransactionImpl implements Transaction, Serializable {
    private static final long serialVersionUID = 9105367311417033838L;
    private SessionImpl session;
    /* Eh? Hibernate's Transaction is not serializable. This should be
     * made transient and null-safe. Anyway it's not likely that this
     * is hit very often. -- RS 2015-06-23 */
	private org.hibernate.Transaction trx;
	private boolean committed = false, rolledBack = false, finished = false;

	protected TransactionImpl(SessionImpl session, org.hibernate.Transaction trx) throws PersistenceException {
		super();
		this.session = session;
		this.trx = trx;
	}

	/**
	 * @see de.odysseus.bizkaia.persistence.Transaction#commit()
	 */
	@Override
    public void commit() throws PersistenceException {
		if (finished)
			throw new PersistenceException(PersistenceException.FINISHED_TRX);
		try {
			trx.commit();
			committed = true;
		} catch (Exception e) {		// SQL, Hibernate
			try {
				rollback();
			} catch (PersistenceException pe) {
			}
			throw new PersistenceException(PersistenceException.COMMIT_TRX, e);
		} finally {
			session.endTransaction();
			finished = true;
		}
	}

	/**
	 * @see de.odysseus.bizkaia.persistence.Transaction#rollback()
	 */
	@Override
    public void rollback() throws PersistenceException {
		if (finished)
			throw new PersistenceException(PersistenceException.FINISHED_TRX);
		try {
			trx.rollback();
			rolledBack = true;
		} catch (Exception e) {		// Hibernate
			throw new PersistenceException(PersistenceException.ROLLBACK_TRX, e);
		} finally {
			session.endTransaction();
			finished = true;
		}
	}

	/**
	 * @see de.odysseus.bizkaia.persistence.Transaction#wasCommited()
	 */
	@Override
    public boolean wasCommitted() {
		return committed;
	}

	/**
	 * @see de.odysseus.bizkaia.persistence.Transaction#wasRolledBack()
	 */
	@Override
    public boolean wasRolledBack() {
		return rolledBack;
	}
}
