package com.rentalcars.enums;

public enum Transmission {

	M("Manual"), A("Automatic");

	private final String value;

	Transmission(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}