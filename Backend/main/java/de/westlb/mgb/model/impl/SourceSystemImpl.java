package de.westlb.mgb.model.impl;

import java.io.Serializable;

public class SourceSystemImpl extends EntityImpl {

	private static final long serialVersionUID = 8973127916336822487L;

    private static final String LAST_LINE_DATE_FORMAT = "dd.MM.yy HH:mm";
	private static final String LAST_LINE_FORMAT = "''#;<NumberOfTrades>;<StartBusinessDate as {0}>;<StopBusinessDate as {0}>;<ExtractionDate as {0}>''";

	private String code;
	private String name;
	private boolean enabled = true;
	private int expectedJobs;
	private String converter;
	private String cobDepends;
	private String filename;
	private String fileExt;
	private String lastLineFormat;
	private String lastLineDateFormat;
	private int maxCheckDiff;
	private int maxLoadErrors;
	private int maxConvertErrors;
	private int maxFileAge;
	private int offset;
	private int cobIndex;
	private String cobFormat;


	
	
	@Override
    public Serializable getId() {
		return code;
	}

	@Override
    protected StringBuffer getProperties() {
		return new StringBuffer("code=" + code);
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

	/**
	 * Returns the systemId.
	 * @return SystemIdImpl
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

	protected SourceSystemImpl() {
	}

	public SourceSystemImpl(String code) {
		this.code = code;
	}
	
	/** 
	 * Print a description of the instrument.
	 * Used for dual control jobs list.
	 * @see java.lang.Object#toString()
	 */
	@Override
    public String toString() {
		return name != null ? name : super.toString();
	}
	
    public void setEnabled(boolean enabled) {
        this.enabled = enabled;
    }

    public boolean getEnabled() {
        return enabled;
    }

    public int getExpectedJobs()
    {
        return expectedJobs;
    }

    public void setExpectedJobs(int expectedJobs)
    {
        this.expectedJobs = expectedJobs;
    }

	public String getConverter() {
		return converter;
	}

	public void setConverter(String converter) {
		this.converter = converter;
	}

	public String getCobDepends() {
		return cobDepends;
	}

	public void setCobDepends(String cobDepends) {
		this.cobDepends = cobDepends;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFileExt() {
		return fileExt;
	}

	public void setFileExt(String fileExt) {
		this.fileExt = fileExt;
	}

	public String getLastLineFormat() {
		return (lastLineFormat == null || lastLineFormat.length() == 0) ? LAST_LINE_FORMAT : lastLineFormat;
	}

	public void setLastLineFormat(String lastLineFormat) {
		this.lastLineFormat = lastLineFormat;
	}

	public String getLastLineDateFormat() {
		return (lastLineDateFormat == null || lastLineDateFormat.length() == 0) ? LAST_LINE_DATE_FORMAT : lastLineDateFormat;
	}

	public void setLastLineDateFormat(String lastLineDateFormat) {
		this.lastLineDateFormat = lastLineDateFormat;
	}

	public int getMaxCheckDiff() {
		return maxCheckDiff;
	}

	public void setMaxCheckDiff(int maxCheckDiff) {
		this.maxCheckDiff = maxCheckDiff;
	}

	public int getMaxLoadErrors() {
		return maxLoadErrors;
	}

	public void setMaxLoadErrors(int maxLoadErrors) {
		this.maxLoadErrors = maxLoadErrors;
	}

	public int getMaxConvertErrors() {
		return maxConvertErrors;
	}

	public void setMaxConvertErrors(int maxConvertErrors) {
		this.maxConvertErrors = maxConvertErrors;
	}

	public int getMaxFileAge() {
		return maxFileAge;
	}

	public void setMaxFileAge(int maxFileAge) {
		this.maxFileAge = maxFileAge;
	}

	public int getOffset() {
		return offset;
	}

	public void setOffset(int offset) {
		this.offset = offset;
	}

	/**
	 * @return the cobIndex
	 */
	public int getCobIndex() {
		return cobIndex;
	}

	/**
	 * @param cobIndex the cobIndex to set
	 */
	public void setCobIndex(int cobIndex) {
		this.cobIndex = cobIndex;
	}

	public String getCobFormat() {
		return cobFormat;
	}

	public void setCobFormat(String cobFormat) {
		this.cobFormat = cobFormat;
	}

}
