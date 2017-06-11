package com.rentalcars.enums;

public enum DoorsCarType {

	B("2 doors"), C("4 doors"), D("5 doors"), W("Estate"), T("Convertible"), F(
			"SUV"), P("Pick up"), V("Passenger Van");

	private final String value;

	DoorsCarType(final String value) {
		this.value = value;
	}

	public String getValue() {
		return value;
	}

}