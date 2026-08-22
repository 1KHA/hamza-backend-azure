package com.hamza.booking.admin.widget.constants;

/**
 * @author Stockfish Technology
 */
public enum BookingStatus {

	AVAILABLE("Available"),
	SCHEDULED("Scheduled"),
	COMPLETED("Completed"),
	CANCELLED("Cancelled");

	private final String value;

	BookingStatus(String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}
