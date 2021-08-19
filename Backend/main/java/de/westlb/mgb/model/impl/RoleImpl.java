package de.westlb.mgb.model.impl;

import java.io.Serializable;

import de.westlb.mgb.model.definition.RoleDef;

/**
 * @author D055625
 *
 * To change this generated comment edit the template variable "typecomment":
 * Window>Preferences>Java>Templates.
 * To enable and disable the creation of type comments go to
 * Window>Preferences>Java>Code Generation.
 */
public class RoleImpl extends EntityImpl implements RoleDef {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8363879644802922546L;

	private String code;

	private String name;

	@SuppressWarnings("unused")
    private RoleImpl() {
	}

	public RoleImpl(String code) {
		this.code = code;
		this.name = code;
	}

	/**
	 * Returns the name.
	 * @return String
	 */
	public String getName() {
		return name;
	}

	/**
	 * Sets the name.
	 * @param name The name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	@Override
    public Serializable getId() {
		return code;
	}

	@Override
    public String toString() {
		return code;
	}

	/**
	 * Returns the code.
	 * @return String
	 */
	public String getCode() {
		return code;
	}

	/**
	 * Sets the code.
	 * @param code The code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

}
