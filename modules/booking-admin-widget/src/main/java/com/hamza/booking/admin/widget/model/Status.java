package com.hamza.booking.admin.widget.model;

/**
 * POJO representing the status information.
 * 
 * @author hamza
 */
public class Status {

	private int code;
	private String label;
	private String label_i18n;

	public int getCode() {
		return code;
	}

	public void setCode(int code) {
		this.code = code;
	}

	public String getLabel() {
		return label;
	}

	public void setLabel(String label) {
		this.label = label;
	}

	public String getLabel_i18n() {
		return label_i18n;
	}

	public void setLabel_i18n(String label_i18n) {
		this.label_i18n = label_i18n;
	}
}

