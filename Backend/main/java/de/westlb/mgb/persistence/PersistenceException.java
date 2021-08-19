/*
 * Created on 
 */
package de.westlb.mgb.persistence;

import de.westlb.mgb.MgbException;

/**
 * MGB persistence 
 * 
 * Created on Sep 29, 2003
 * 
 * @author Manfred Boerner
 */
public class PersistenceException extends MgbException {
	/**
	 * 
	 */
	private static final long serialVersionUID = 8370901836670877457L;
	public static final String				// TODO: fill corresponding resource file
		CREATE_STORE = "create.store", INIT_STORE = "init.store",
		OPEN_SESSION = "open.session", CLOSE_SESSION = "close.session",
		BEGIN_TRX = "begin.trx",
		COMMIT_TRX = "commit.trx", ROLLBACK_TRX = "rollback.trx",
		FLUSH_TRX = "flush.trx", CLEAR_TRX = "clear.trx",
		FINISHED_TRX = "finished.trx", MISSING_TRX = "missing.trx",
		ILLEGAL_TYPE = "illegal.type",
		CREATE_OBJ = "create.obj", SELECT_OBJ = "select.obj",
		SAVE_OBJ = "save.obj", DELETE_OBJ = "delete.obj",
		CREATE_QRY = "create.qry",
		EXEC_NATIVE_SQL = "execute.native.sql",
		EXEC_UPDATE = "execute.update",
		EXEC_INSERT = "execute.insert";

	/**
	 * Creates a new instance.
	 * @param msg
	 */
	public PersistenceException(String msg) {
		super(msg);
	}

	/**
	 * Creates a new instance.
	 * @param msg
	 * @param par
	 */
	public PersistenceException(String msg, Object par) {
		super(msg, par);
	}

	/**
	 * Creates a new instance.
	 * @param msg
	 * @param par1
	 * @param par2
	 */
	public PersistenceException(String msg, Object par1, Object par2) {
		super(msg, par1, par2);
	}

	/**
	 * Creates a new instance.
	 * @param msg
	 * @param par1
	 * @param par2
	 * @param par3
	 */
	public PersistenceException(String msg, Object par1, Object par2, Object par3) {
		super(msg, par1, par2, par3);
	}

	/**
	 * Creates a new instance.
	 * @param msg
	 * @param par
	 * @param cause
	 */
	public PersistenceException(String msg, Object par, Throwable cause) {
		super(msg, par, cause);
	}

	/**
	 * Creates a new instance.
	 * @param msg
	 * @param par1
	 * @param par2
	 * @param cause
	 */
	public PersistenceException(String msg, Object par1, Object par2, Throwable cause) {
		super(msg, par1, par2, cause);
	}

	/**
	 * Creates a new instance.
	 * @param msg
	 * @param par1
	 * @param par2
	 * @param par3
	 * @param cause
	 */
	public PersistenceException(String msg, Object par1, Object par2, Object par3, Throwable cause) {
		super(msg, par1, par2, par3, cause);
	}
}
