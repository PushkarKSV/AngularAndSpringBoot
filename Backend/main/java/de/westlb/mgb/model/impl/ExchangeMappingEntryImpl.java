package de.westlb.mgb.model.impl;
/** @modelguid {39A11319-4004-409F-A2A2-4DAC03BA1CE0} */
public class ExchangeMappingEntryImpl extends EntityImpl {

	/**
     * 
     */
    private static final long serialVersionUID = -7510482528264365044L;

    /** @modelguid {37B095C2-BB95-49D0-B43A-6165E274D739} */
	private String isin;

	/** @modelguid {9CF572B6-DFFF-4BA4-A7A4-5F55385C7DF2} */
	private SourceSystemImpl sourceSystem;

	/** @modelguid {AF430F02-4195-4C70-B019-1538E423A004} */
	private String isinCountryPrefix;

	/** @modelguid {CBD44339-C23F-4E73-A7C5-2C557D4089BA} */
	private String sourceSystemExchangeId;

	/** @modelguid {89CD25AD-7F77-407A-8402-68B49182278B} */
	private String currency;

	/** @modelguid {ED579294-800F-42BD-BB27-0A99F2F9008E} */
	private ExchangeImpl exchange;

	private ExchangeMappingEntryPrioImpl mappingPrio;

	/** @modelguid {D1AA4C03-78EA-4788-A407-0CDBB3B67DB7} */
	@Override
    public Long getLongId() {
		return super.getLongId();
	}

	public String buildRequestKey() {
		String sourceSystemCode = "null";
		if (sourceSystem != null) {
			sourceSystemCode = sourceSystem.getCode();
		}
		return sourceSystemCode + "_" + sourceSystemExchangeId + "_" + isin + "_" + isinCountryPrefix + "_" + currency;
	}

	public static String buildRequestKey(String sourceSystemCode, String sourceSystemExchangeId, String isin, String currency) {
		if (isin != null && isin.length() > 1) {
			return sourceSystemCode + "_" + sourceSystemExchangeId + "_" + isin + "_" + isin.substring(0, 2) + "_" + currency;
		}
        return sourceSystemCode + "_" + sourceSystemExchangeId + "_" + isin + "_null_" + currency;
	}

	public static String buildRequestKey(
		String sourceSystem,
		String sourceSystemExchangeId,
		String isin,
		String currency,
		ExchangeMappingEntryPrioImpl mappingPrio) {
		if (mappingPrio != null) {
			StringBuffer result = new StringBuffer();
			if (mappingPrio.isSourceSystemNotNull()) {
				result.append(sourceSystem);
			} else {
				result.append("null");
			}
			result.append("_");

			if (mappingPrio.isSourceSystemExchangeIdNotNull()) {
				result.append(sourceSystemExchangeId);
			} else {
				result.append("null");
			}
			result.append("_");

			if (mappingPrio.isIsinNotNull()) {
				result.append(isin);
			} else {
				result.append("null");
			}
			result.append("_");

			if (mappingPrio.isIsinCountryPrefixNotNull() && isin != null && isin.length() > 1) {
				result.append(isin.substring(0, 2));
			} else {
				result.append("null");
			}
			result.append("_");

			if (mappingPrio.isCurrencyNotNull()) {
				result.append(currency);
			} else {
				result.append("null");
			}
			return result.toString();
		}
        return buildRequestKey(sourceSystem, sourceSystemExchangeId, isin, currency);
	}

	/**
	 * Returns the currency.
	 * @return String
	 * @modelguid {334AA496-ADE9-47DE-A09C-80C71C7FE27F}
	 */
	public String getCurrency() {
		return currency;
	}

	/**
	 * Returns the exchange.
	 * @return ExchangeImpl
	 * @modelguid {0E33C12B-3ADE-410B-9D26-2C4028BC6A26}
	 */
	public ExchangeImpl getExchange() {
		return exchange;
	}

	/**
	 * Returns the isin.
	 * @return String
	 * @modelguid {651F133E-D352-46C4-A4C5-BD562CCDDB1A}
	 */
	public String getIsin() {
		return isin;
	}

	/**
	 * Returns the isinCountryPrefix.
	 * @return String
	 * @modelguid {DCC8D7B8-2A38-4C73-8C83-D7682C6612E6}
	 */
	public String getIsinCountryPrefix() {
		return isinCountryPrefix;
	}

	/**
	 * Returns the sourceSystem.
	 * @return SourceSystemImpl
	 * @modelguid {08B46975-7DF0-48F1-8D39-8D0ECD269D0D}
	 */
	public SourceSystemImpl getSourceSystem() {
		return sourceSystem;
	}

	/**
	 * Returns the sourceSystemExchangeId.
	 * @return String
	 * @modelguid {E92EF808-FA00-42B7-BAAF-DC3DD1FF9E08}
	 */
	public String getSourceSystemExchangeId() {
		return sourceSystemExchangeId;
	}

	/**
	 * Sets the currency.
	 * @param currency The currency to set
	 * @modelguid {72100335-B918-40EA-B843-536097A4A423}
	 */
	public void setCurrency(String currency) {
		this.currency = currency;
	}

	/**
	 * Sets the exchange.
	 * @param exchange The exchange to set
	 * @modelguid {F4D92962-42C5-4804-93E5-603F1F4BDC6A}
	 */
	public void setExchange(ExchangeImpl exchange) {
		this.exchange = exchange;
	}

	/**
	 * Sets the isin.
	 * @param isin The isin to set
	 * @modelguid {AC19451A-B648-465C-80FB-698ADF06E267}
	 */
	public void setIsin(String isin) {
		this.isin = isin;
	}

	/**
	 * Sets the isinCountryPrefix.
	 * @param isinCountryPrefix The isinCountryPrefix to set
	 * @modelguid {F55CF6ED-C730-4559-BF32-4C2F7263ECF9}
	 */
	public void setIsinCountryPrefix(String isinCountryPrefix) {
		this.isinCountryPrefix = isinCountryPrefix;
	}

	/**
	 * Sets the sourceSystem.
	 * @param sourceSystem The sourceSystem to set
	 * @modelguid {3A02D0BA-3763-4299-A378-F2E63A1A781B}
	 */
	public void setSourceSystem(SourceSystemImpl sourceSystem) {
		this.sourceSystem = sourceSystem;
	}

	/**
	 * Sets the sourceSystemExchangeId.
	 * @param sourceSystemExchangeId The sourceSystemExchangeId to set
	 * @modelguid {539987FB-57D7-421D-B881-176FBB365449}
	 */
	public void setSourceSystemExchangeId(String sourceSystemExchangeId) {
		this.sourceSystemExchangeId = sourceSystemExchangeId;
	}

	/** 
	 * Print a description of the instrument.
	 * Used for dual control jobs list.
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		StringBuffer sb = new StringBuffer();

		if (exchange != null) {
			sb.append("Exchange: ");
			sb.append(exchange.toString());
			;
			sb.append(' ');
		}

		sb.append("Mappping: ");

		if (isin != null) {
			sb.append(isin);
			sb.append(' ');
		};

		if (sourceSystem != null) {
			sb.append(sourceSystem);
			sb.append(' ');
		};
		if (isinCountryPrefix != null) {
			sb.append(sourceSystem);
			sb.append(' ');
		}
		if (sourceSystemExchangeId != null) {
			sb.append(sourceSystemExchangeId);
			sb.append(' ');
		}
		if (currency != null) {
			sb.append(currency);
			sb.append(' ');
		}

		return sb.toString();
	}
	/**
	 * Returns the prio.
	 * @return ExchangeMappingEntryPrioImpl
	 */
	public ExchangeMappingEntryPrioImpl getMappingPrio() {
		return mappingPrio;
	}

	/**
	 * Sets the prio.
	 * @param prio The prio to set
	 */
	public void setMappingPrio(ExchangeMappingEntryPrioImpl prio) {
		this.mappingPrio = prio;
	}

}
