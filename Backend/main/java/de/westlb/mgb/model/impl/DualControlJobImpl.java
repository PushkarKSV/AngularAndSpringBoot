package de.westlb.mgb.model.impl;

import java.sql.Blob;

import de.westlb.mgb.model.Entity;

/**
 * 
 * @modelguid {CCCA5C7C-F245-4776-B892-7DB9D403AF69}
 */
public class DualControlJobImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -6904372860217535950L;

    /** @modelguid {F7AD2579-9B90-4334-9B54-C9EF61649791} */
	private String operation;

	/** @modelguid {4D081E2C-B29C-49F3-81FF-14B6A12CA1DD} */
	private Blob persistentObject;

	/** @modelguid {8F330216-E580-4858-8E80-DB36F92E5E23} */
	private String description;

	/** @modelguid {01DEC58A-CBFF-49D9-88A0-9A7A6B5C39E3} */
	private String entityTypeName;

	/** @modelguid {2444C110-EF8B-400A-8CDB-E07FBC6FA53B} */
	private String state;

	/** @modelguid {903EADDF-CD43-49FD-9F25-9614F1C6831F} */
	private AddonImpl addon;

	/** @modelguid {D97A152C-BC1E-4E8D-BA4C-22B174FD13BE} */
	private PriceCheckCategoryImpl priceCheckCategory;

	/** @modelguid {FF3F44BB-2F0A-43CB-B702-CD5EC9266AA8} */
	private ExchangeImpl exchange;

	/** @modelguid {6823E054-9225-40C8-AB3F-6D88EEFD4E46} */
	private ExchangeMappingEntryImpl exchangeMappingEntry;

	/** @modelguid {66F79A78-245D-4374-B59F-E2F01499B48C} */
	private InstrumentImpl instrument;

	private MgbConfigurationImpl mgbConfiguration;

	/** @modelguid {F8249CB5-467F-4EED-AF2E-81E4807128A6} */
	private EmployeeImpl requestedBy;

	/** @modelguid {ABD5C7ED-526B-4705-A647-5DE51E34DDBB} */
	private EmployeeImpl commitedBy;
	
	/** @modelguid {9AEDFBC6-8E52-4495-9DD4-D2084A330632} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	/** @modelguid {835E7957-2506-4FEB-853B-4173B40FA178} */
	public boolean setEntity(Entity o) {
		if ( o == null) {
			this.addon = null;
			this.priceCheckCategory = null;
			this.exchange = null;
			this.exchangeMappingEntry = null;
			return true;
		} else if (o instanceof AddonImpl) {
			this.addon = (AddonImpl) o;
			return true;
		} else if (o instanceof PriceCheckCategoryImpl) {
			this.priceCheckCategory = (PriceCheckCategoryImpl) o;
			return true;
		} else if (o instanceof ExchangeImpl) {
			this.exchange = (ExchangeImpl) o;
			return true;
		} else if (o instanceof ExchangeMappingEntryImpl) {
			this.exchangeMappingEntry = (ExchangeMappingEntryImpl) o;
			return true;
		} else if (o instanceof InstrumentImpl) {
			this.instrument = (InstrumentImpl) o;
			return true;
		} else if (o instanceof MgbConfigurationImpl) {
			this.mgbConfiguration = (MgbConfigurationImpl) o;
			return true;
		} else {
			return false;
		}
	}

	/** @modelguid {8EBB66BB-2CA5-428D-AA03-B737BAC95547} */
	public Entity getEntity() {
		if (addon != null) {
			return addon;
		} else if (priceCheckCategory != null) {
			return priceCheckCategory;
		} else if (exchange != null) {
			return exchange;
		} else if (exchangeMappingEntry != null) {
			return exchangeMappingEntry;
		} else if (instrument != null) {
			return instrument;
		} else if (mgbConfiguration != null) {
			return mgbConfiguration;
		} else {
			return null;
		}
	}

	/**
	 * Returns the commitedBy.
	 * @return EmployeeImpl
	 * @modelguid {27CED450-101D-47B3-A2D1-709B4CB62B25}
	 */
	public EmployeeImpl getCommitedBy() {
		return commitedBy;
	}

	/**
	 * Returns the description.
	 * @return String
	 * @modelguid {E8F28E27-20D5-440E-B41D-3294FB9659BD}
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Returns the operation.
	 * @return int
	 * @modelguid {E34D246D-1CE7-44FB-B682-44F853C502D7}
	 */
	public String getOperation() {
		return operation;
	}

	/**
	 * Returns the requestedBy.
	 * @return EmployeeImpl
	 * @modelguid {294A81F9-9767-4D04-9DF5-AA09834FE73C}
	 */
	public EmployeeImpl getRequestedBy() {
		return requestedBy;
	}

	/**
	 * Sets the commitedBy.
	 * @param commitedBy The commitedBy to set
	 * @modelguid {3A849220-D4EB-460E-95ED-7497002434D0}
	 */
	public void setCommitedBy(EmployeeImpl commitedBy) {
		this.commitedBy = commitedBy;
	}

	/**
	 * Sets the description.
	 * @param description The description to set
	 * @modelguid {2570F61A-3956-470D-AC01-0D140C13FFEE}
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * Sets the operation.
	 * @param operation The operation to set
	 * @modelguid {A1058833-BD6C-4B33-9BE9-4846E5B15250}
	 */
	public void setOperation(String operation) {
		this.operation = operation;
	}

	/**
	 * Sets the requestedBy.
	 * @param requestedBy The requestedBy to set
	 * @modelguid {466E2947-07A4-43DD-9D59-6E401CF9CEDC}
	 */
	public void setRequestedBy(EmployeeImpl requestedBy) {
		this.requestedBy = requestedBy;
	}

	/**
	 * Sets the persistentObject.
	 * @param persistentObject The persistentObject to set
	 * @modelguid {88F9D630-9C57-4335-99CC-8A803560258B}
	 */
	public void setPersistentObject(Blob persistentObject) {
		this.persistentObject = persistentObject;
	}

	/**
	 * Returns the persistentObject.
	 * @return Object
	 * @modelguid {08E328B9-C36E-45FC-A5AC-1B896C8B6B6F}
	 */
	public Blob getPersistentObject() {
		return persistentObject;
	}

	/**
	 * Returns the addon.
	 * @return AddonImpl
	 * @modelguid {F93918C0-0C60-42E3-A479-83F0F41512AA}
	 */
	public AddonImpl getAddon() {
		return addon;
	}

	/**
	 * Returns the entityTypeName.
	 * @return String
	 * @modelguid {8157835C-5974-41F3-85D6-35C953A0811C}
	 */
	public String getEntityTypeName() {
		return entityTypeName;
	}

	/**
	 * Returns the exchange.
	 * @return ExchangeImpl
	 * @modelguid {ED2D3E94-5457-4133-9750-59FE7D291D95}
	 */
	public ExchangeImpl getExchange() {
		return exchange;
	}

	/**
	 * Returns the exchangeMappingEntry.
	 * @return ExchangeMappingEntryImpl
	 * @modelguid {8CAE522B-8277-4FDD-8366-6775AC9BA35F}
	 */
	public ExchangeMappingEntryImpl getExchangeMappingEntry() {
		return exchangeMappingEntry;
	}

	/**
	 * Returns the priceCheckCategory.
	 * @return PriceCheckCategoryImpl
	 * @modelguid {3DDC28EF-3F75-471A-958C-48E1F52AB3E6}
	 */
	public PriceCheckCategoryImpl getPriceCheckCategory() {
		return priceCheckCategory;
	}

	/**
	 * Returns the state.
	 * @return String
	 * @modelguid {82E85475-4BBC-4DA8-9879-06360FF1C97C}
	 */
	public String getState() {
		return state;
	}

	/**
	 * Sets the addon.
	 * @param addon The addon to set
	 * @modelguid {5372A040-D175-41B9-8985-04E84E9DFB5C}
	 */
	public void setAddon(AddonImpl addon) {
		this.addon = addon;
	}

	/**
	 * Sets the entityTypeName.
	 * @param entityTypeName The entityTypeName to set
	 * @modelguid {71DC05EB-A4B7-4B84-B7B4-2C16DB8E3AC4}
	 */
	public void setEntityTypeName(String entityTypeName) {
		this.entityTypeName = entityTypeName;
	}

	/**
	 * Sets the exchange.
	 * @param exchange The exchange to set
	 * @modelguid {47244185-474D-4F59-9091-347B4C26E556}
	 */
	public void setExchange(ExchangeImpl exchange) {
		this.exchange = exchange;
	}

	/**
	 * Sets the exchangeMappingEntry.
	 * @param exchangeMappingEntry The exchangeMappingEntry to set
	 * @modelguid {F4AC86AE-7461-487B-9E55-AAA8D4F43842}
	 */
	public void setExchangeMappingEntry(ExchangeMappingEntryImpl exchangeMappingEntry) {
		this.exchangeMappingEntry = exchangeMappingEntry;
	}

	/**
	 * Sets the priceCheckCategory.
	 * @param priceCheckCategory The priceCheckCategory to set
	 * @modelguid {9D4E45BF-2177-410C-86AB-E00B25B09F57}
	 */
	public void setPriceCheckCategory(PriceCheckCategoryImpl priceCheckCategory) {
		this.priceCheckCategory = priceCheckCategory;
	}

	/**
	 * Sets the state.
	 * @param state The state to set
	 * @modelguid {6ADB63FE-FAFC-46D7-B8A3-E29772CBE81A}
	 */
	public void setState(String state) {
		this.state = state;
	}

	/**
	 * Returns the instrument.
	 * @return InstrumentImpl
	 * @modelguid {7F8D39A2-828E-49C2-8B9B-F16C34FFAE24}
	 */
	public InstrumentImpl getInstrument() {
		return instrument;
	}

	/**
	 * Sets the instrument.
	 * @param instrument The instrument to set
	 * @modelguid {8728E8D6-7DBD-4221-B3D1-A48C3E22C472}
	 */
	public void setInstrument(InstrumentImpl instrument) {
		this.instrument = instrument;
	}

	/**
	 * Returns the mgbConfiguration.
	 * @return MgbConfigurationImpl
	 */
	public MgbConfigurationImpl getMgbConfiguration() {
		return mgbConfiguration;
	}

	/**
	 * Sets the mgbConfiguration.
	 * @param mgbConfiguration The mgbConfiguration to set
	 */
	public void setMgbConfiguration(MgbConfigurationImpl mgbConfiguration) {
		this.mgbConfiguration = mgbConfiguration;
	}

}
