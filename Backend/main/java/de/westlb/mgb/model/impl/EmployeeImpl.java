package de.westlb.mgb.model.impl;

import java.util.Calendar;
import java.util.Collection;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

import de.westlb.mgb.model.definition.RoleDef;

/**
 * 
 * @modelguid {F91A1C26-C18A-4AF7-8B02-7DD1AD4A6B30}
 */
public class EmployeeImpl extends EntityImpl implements RoleDef  {
	/**
     * 
     */
    private static final long serialVersionUID = 1054367940381811375L;

    // longId2 contains the same information than longId2. It exists, because of
	// Hibernate bug with "group by" statements. 
	private long longId2;
	
	/** @modelguid {354BFA19-364D-4C39-B293-B1141DC70ABF} */
	private String firstName;

	/** @modelguid {BB65FEB9-702F-4FF5-B22E-7BC64A6F0850} */
	private String lastName;

	/** @modelguid {4ADC4E28-E925-412A-9312-5CB1CBB9677B} */
	private String email;

	/** @modelguid {C7D1C8EE-C4B8-4284-944D-B3AB57A362A8} */
	private String phone;

	/** @modelguid {C10B32FD-5E25-469F-B540-976E251FF3EC} */
	private Set<UserRoleImpl> roles;

	/** @modelguid {2A6E0082-E28B-4E21-952E-D2F6182010EB} */
	private Set<TraderImpl> traders;

	/** @modelguid {2427B3AA-8A97-4C4D-A5FF-6D70462D0DC4} */
	private String ntId;
	
	private Calendar lastLoginDate;

	/** @modelguid {6E1523AE-2D36-4FCA-A253-DBDEFD20D2A8} */
	public EmployeeImpl() {
	}

	/** @modelguid {41E89D54-5233-4FFD-ABF6-BC16315B8EEC} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/** @modelguid {2B3A744C-493B-4CB5-B503-E33210A98866} */
	public String getFullName() {
		return createFullName(firstName, lastName);
	}
	
	public static String createFullName(String firstName, String lastName) {
		return firstName + " " + lastName;
	}

	/**
	 * Returns the lastName.
	 * @return String
	 * @modelguid {00F8F0C0-8E8E-4327-B41F-76589049A0B6}
	 */
	public String getLastName() {
		return lastName;
	}


	/**
	 * Returns the phone.
	 * @return String
	 * @modelguid {7DEAD4E3-E1DB-4515-991E-FB5BE9719212}
	 */
	public String getPhone() {
		return phone;
	}

	/**
	 * Returns the roles.
	 * @return ArrayList
	 * @modelguid {9D5EA30A-B186-4977-8D17-945BEF67F3AF}
	 */
	public Collection<UserRoleImpl> getRoles() {
		return roles;
	}

	/**
	 * Returns the traderIds.
	 * @return ArrayList
	 * @modelguid {2A7FE64F-12F1-48C5-A0D4-F0228451E950}
	 */
	public Collection<TraderImpl> getTraders() {
		return traders;
	}

	/**
	 * Sets the lastName.
	 * @param lastName The lastName to set
	 * @modelguid {F0CB0BD9-0223-48BA-A743-3555FD2941EB}
	 */
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	/**
	 * Sets the phone.
	 * @param phone The phone to set
	 * @modelguid {FCF13580-61AB-4345-B591-DFBDE1039CA9}
	 */
	public void setPhone(String phone) {
		this.phone = phone;
	}

	/**
	 * Returns the email.
	 * @return String
	 * @modelguid {D9B97042-AECA-47E5-B318-FB07559E7250}
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Returns the firstName.
	 * @return String
	 * @modelguid {C161BD26-F94F-4DE8-9CBB-5DE6A82E2C05}
	 */
	public String getFirstName() {
		return firstName;
	}
	
	/** @modelguid {A48218EC-6595-4D70-AC90-EF7325B1C103} */
	public boolean hasUserRole(RoleImpl roleType) {
		boolean found = false;
		if (getRoles() != null) {
		    Iterator<UserRoleImpl> roles = getRoles().iterator();
		    while (!found && roles.hasNext()) {
		        UserRoleImpl role = roles.next();
		        found = roleType.equals(role.getType());
		    }
		}
        return found;
	}

	public String getReport() {
        if (getRoles() != null) {
            Iterator<UserRoleImpl> roles = getRoles().iterator();
            while (roles.hasNext()) {
                UserRoleImpl role = roles.next();
                if ( role.getLocation() != null) {
                    return role.getLocation(); 
                }
            }
        }
        return null;
	}

	   public void setReport(String report) {
	        if (getRoles() != null) {
	            Iterator<UserRoleImpl> roles = getRoles().iterator();
	            while (roles.hasNext()) {
	                UserRoleImpl role = roles.next();
	                if ( role.isRole(CONTROLLER) ||
	                         role.isRole(ADMIN) ||
	                         role.isRole(SYSTEM_ADMIN) ||
	                         role.isRole(REPORTER) ||
	                         role.isRole(READ_ONLY) ) {
	                    role.setLocation(report); 
	                }
	            }
	        }
	    }

	public boolean hasTraderRole() {
		return hasUserRole(new RoleImpl(TRADER));
	}		

	public boolean hasControllerRole() {
		return hasUserRole(new RoleImpl(CONTROLLER));
	}		

	public boolean hasAdminRole() {
		return hasUserRole(new RoleImpl(ADMIN));
	}		

	public boolean hasSystemAdminRole() {
		return hasUserRole(new RoleImpl(SYSTEM_ADMIN));
	}		

	public boolean hasUserMaintainAdminRole() {
		return hasUserRole(new RoleImpl(USER_MAINTAIN_ADMIN));
	}		

	public boolean hasSparkassenAdminRole() {
	    return hasUserRole(new RoleImpl(SPK_ADMIN));
	}       

    public boolean hasReporterRole() {
        return hasUserRole(new RoleImpl(REPORTER));
    }       

    public boolean hasReadOnlyRole() {
        return hasUserRole(new RoleImpl(READ_ONLY));
    }       
/**
	 * Sets the email.
	 * @param email The email to set
	 * @modelguid {49A1FDC8-B863-4753-96DC-8FD5E80BC190}
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Sets the firstName.
	 * @param firstName The firstName to set
	 * @modelguid {05645A1C-01F1-4614-86F5-022D62198A2D}
	 */
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	/**
	 * Sets the roles.
	 * @param roles The roles to set
	 * @modelguid {49A98145-751F-4BF3-9B0B-49F293AD62A9}
	 */
	@SuppressWarnings("unused")
    private void setRoles(Set<UserRoleImpl> roles) {
		this.roles = roles;
	}

	/** @modelguid {A58D8A5B-1816-4EBF-940B-576BA69A2B12} */
	@Deprecated
	public boolean addTraderRole() {
		return addRole(new RoleImpl(TRADER), null);
	}		

	/** @modelguid {DA681A21-A7EC-472D-AD57-3219CBD08985} */
    @Deprecated
	public boolean addControllerRole() {
		return addRole(new RoleImpl(CONTROLLER), null);
	}		

    @Deprecated
    public boolean addControllerRole(String location) {
        return addRole(new RoleImpl(CONTROLLER), location);
    }       

    @Deprecated
    public boolean addAdminRole() {
		return addRole(new RoleImpl(ADMIN), null);
	}		

    @Deprecated
	public boolean addUserMaintainAdminRole() {
		return addRole(new RoleImpl(USER_MAINTAIN_ADMIN), null);
	}		

    @Deprecated
	public boolean addAutoControllerRole() {
		return addRole(new RoleImpl(AUTO_CONTROLLER), null);
	}		

    @Deprecated
	public boolean addSystemAdminRole() {
		return addRole(new RoleImpl(SYSTEM_ADMIN), null);
	}		

    @Deprecated
	public boolean addSparkassenAdminRole() {
        return addRole(new RoleImpl(SPK_ADMIN), null);
    }       

	public boolean addUserRole(UserRoleImpl role) {
	    role.setEmployee(this);
        if (roles != null && roles.size() > 0) {
            boolean isNewRole = true;
            for (Iterator<UserRoleImpl> i = roles.iterator();i.hasNext();) {
                if ( role.getType().equals( i.next().getType() ) ) {
                    isNewRole = false;
                }
            }
            if (isNewRole) {
                return roles.add(role);
            }
        } else {
            roles = new HashSet<UserRoleImpl>();
            return roles.add(role);
        }
        return false;
	}

	/** @modelguid {DACF1C4C-515C-4666-AFEC-013FDE8B5533} */
    @Deprecated
	private boolean addRole(RoleImpl roleType, String location) {
		UserRoleImpl role = new UserRoleImpl();
		role.setEmployee(this);
		role.setType(roleType);
		role.setLocation(location);
		if (roles != null && roles.size() > 0) {
			boolean isNewRole = true;
			for (Iterator<UserRoleImpl> i = roles.iterator();i.hasNext();) {
				if ( roleType != null && roleType.equals( i.next().getType() ) ) {
					isNewRole = false;
				}
			}
			if (isNewRole) {
				return roles.add(role);
			}
		} else {
			roles = new HashSet<UserRoleImpl>();
			return roles.add(role);
		}
		return false;
	}

	/** @modelguid {EA03E157-362D-45EC-96D0-E813640FB89E} */
	public boolean removeTraderRole() {
		// private due to problems with hibernate cascade option "all-delete-orphan"
		return removeRole(new RoleImpl(TRADER));
	}		

	/** @modelguid {77B4A8E6-15CE-4229-A31A-3FB0B8407D5C} */
	public boolean removeControllerRole() {
		// private due to problems with hibernate cascade option "all-delete-orphan"
		return removeRole(new RoleImpl(CONTROLLER));
	}		

	public boolean removeAutoControllerRole() {
		// private due to problems with hibernate cascade option "all-delete-orphan"
		return removeRole(new RoleImpl(AUTO_CONTROLLER));
	}		

	public boolean removeAdminRole() {
		// private due to problems with hibernate cascade option "all-delete-orphan"
		return removeRole(new RoleImpl(ADMIN));
	}		

	public boolean removeUserMaintainAdminRole() {
		// private due to problems with hibernate cascade option "all-delete-orphan"
		return removeRole(new RoleImpl(USER_MAINTAIN_ADMIN));
	}
	
	public boolean removeSystemAdminRole() {
		// private due to problems with hibernate cascade option "all-delete-orphan"
		return removeRole(new RoleImpl(SYSTEM_ADMIN));
	}		

    public boolean removeSparkasenAdminRole() {
        // private due to problems with hibernate cascade option "all-delete-orphan"
        return removeRole(new RoleImpl(SPK_ADMIN));
    }       

    /** @modelguid {A94BE9B8-45E3-4170-BA03-B96BF3EDD075} */
	private boolean removeRole(RoleImpl roleType) {
		if (roles != null && roles.size() > 0) {
			for (Iterator<UserRoleImpl> i = roles.iterator();i.hasNext();) {
				UserRoleImpl role = i.next();
				if ( roleType != null && roleType.equals( role.getType() ) ) {
					//return roles.remove(role);
				    i.remove();
				    return true;
				}
			}
		}
		return false; 
	}

	/** @modelguid {8656ECB0-926D-4FDE-93E6-23AE73E5DE40} */
	public boolean addTraderId(TraderImpl traderId) {
		traderId.setEmployee(this);
		if (traders != null && traders.size() > 0) {
				return traders.add(traderId);
		}
        traders = new HashSet<TraderImpl>();
        return traders.add(traderId);
	}

	/** @modelguid {4019E659-6C84-4CF5-8D93-9678C536F4D5} */
	@SuppressWarnings("unused")
    private boolean removeTraderId(TraderImpl traderId) {
		return traders.remove(traderId); 
	}

	/**
	 * Sets the traderIds.
	 * @param traderIds The traderIds to set
	 * @modelguid {F702B206-EFB8-4684-B9F1-07615E215DC1}
	 */
	public void setTraders(Set<TraderImpl> traderIds) {
		this.traders = traderIds;
	}


	/**
	 * Returns the ntId.
	 * @return String
	 * @modelguid {36DB500F-D98C-40D7-BBFF-B1879CF59E1E}
	 */
	public String getNtId() {
		return ntId;
	}

	/**
	 * Sets the ntId.
	 * @param ntId The ntId to set
	 * @modelguid {846EE92D-C4EE-4258-AA30-90E749983789}
	 */
	public void setNtId(String ntId) {
		this.ntId = ntId;
	}

    /**
     * @return
     */
    public long getLongId2() {
        return longId2;
    }

    /**
     * @param l
     */
    public void setLongId2(long l) {
        longId2 = l;
    }

    public void setLastLoginDate(Calendar lastLoginDate) {
        this.lastLoginDate = lastLoginDate;
    }

    public Calendar getLastLoginDate() {
        return lastLoginDate;
    }

}

