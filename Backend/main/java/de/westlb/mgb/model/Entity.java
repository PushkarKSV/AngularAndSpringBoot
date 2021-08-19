/*
 * Created on 02.06.2003
 */
package de.westlb.mgb.model;

import java.io.Serializable;

/**
 * Entity interface.
 *
 * @author Oliver Stuhr
 */
public interface Entity {
	/**
	 * get objectId property
	 */
	Serializable getId();
	void setNullId();
}
