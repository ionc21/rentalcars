package com.rentalcars.enums;

public enum FuelAC {

	N("Petrol / No AC"), R("Petrol / AC");

	private final String value;

	FuelAC(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}
}