/*
 * Created on 03.06.2003
 */
package de.westlb.mgb.model.impl;

import java.io.Serializable;
import java.util.Calendar;

import org.apache.log4j.Logger;

import de.westlb.mgb.model.Entity;

/** @modelguid {8A5CAFFE-F84F-4053-AF9E-51A109182667} */
public abstract class EntityImpl implements Entity, Serializable, Comparable<EntityImpl> {

    private static final long serialVersionUID = -1115493446865551293L;

    static Logger logger = Logger.getLogger(EntityImpl.class);

	private Long longId = null;

	private Calendar creationDate;
	private Calendar changedDate;
	private String createdBy;
	private String changedBy;
	private MandantImpl mandant;

	/**
	 * Creates a new instance.
	 */
	protected EntityImpl() {
		super();
	}

	protected EntityImpl(Long id) {
		super();
		this.longId = id;
	}

    /**
     * @see java.lang.Comparable#compareTo(java.lang.Object)
     */
    @Override
    @SuppressWarnings({ "unchecked", "rawtypes"})
    public int compareTo(EntityImpl that) {
        if (this == null || that == null) {
            return 0;
        }
        return ((Comparable)getId()).compareTo(that.getId());
    }

	/**
	 * @see java.lang.Object#equals(Object)
	 */
	@Override
    public boolean equals(Object that) {
		if (this == that)
			return true;
		if (!(that instanceof Entity) || getId() == null)
			return false;
		return getId().equals(((Entity) that).getId());
	}

	@Override
    public int hashCode() {
		if (getId() == null) {
			logger.error("Hashcode of a non-perstant EntityImpl is generated from the ID");
			return 0;
		}
        return getId().hashCode();
	}

	public String getModifiedBy() {
		if ( changedBy != null && changedBy.length() > 0 ) {
			return changedBy;
		}
        return createdBy;
	}

	public Calendar getModifiedDate() {
		if ( changedDate != null ) {
			return changedDate;
		}
        return creationDate;
	}

	/**
	 * @see de.odysseus.bizkaia.model.Entity#getId()
	 */
	@Override
    public Serializable getId() {
		return longId;
	}

	@Override
    public void setNullId() {
		longId = null;
	}

	protected Long getLongId() {
		return longId;
	}

	@SuppressWarnings("unused")
    private void setLongId(Long id) {
		longId = id;
	}

	/**
	 * Answers the property names and values of the receiver.
	 * To be overridden by subclasses with additional properties.
	 *
	 * @return StringBuffer
	 */
	protected StringBuffer getProperties() {
		return new StringBuffer("longId=" + longId);
	}

	/**
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return getClass().getName() + " [" + getProperties() + "]";
	}

	/**
	 * Returns the creationDate.
	 * @return Date
	 */
	public Calendar getCreationDate() {
		return creationDate;
	}

	/**
	 * Returns the mandant.
	 * @return MandantImpl
	 */
	public MandantImpl getMandant() {
		return mandant;
	}

	/**
	 * Sets the creationDate.
	 * @param creationDate The creationDate to set
	 */
	public void setCreationDate(Calendar creationDate) {
		this.creationDate = creationDate;
	}

	/**
	 * Sets the mandant.
	 * @param mandant The mandant to set
	 */
	public void setMandant(MandantImpl mandant) {
		this.mandant = mandant;
	}

	/**
	 * Returns the changedDate.
	 * @return Calendar
	 */
	public Calendar getChangedDate() {
		return changedDate;
	}

	/**
	 * Sets the changedDate.
	 * @param changedDate The changedDate to set
	 */
	public void setChangedDate(Calendar changedDate) {
		this.changedDate = changedDate;
	}

	/**
	 * Returns the changedBy.
	 * @return String
	 */
	public String getChangedBy() {
		return changedBy;
	}

	/**
	 * Returns the createdBy.
	 * @return String
	 */
	public String getCreatedBy() {
		return createdBy;
	}

	/**
	 * Sets the changedBy.
	 * @param changedBy The changedBy to set
	 */
	public void setChangedBy(String changedBy) {
		this.changedBy = changedBy;
	}

	/**
	 * Sets the createdBy.
	 * @param createdBy The createdBy to set
	 */
	public void setCreatedBy(String createdBy) {
		this.createdBy = createdBy;
	}

}