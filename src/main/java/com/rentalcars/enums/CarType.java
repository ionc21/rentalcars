package com.rentalcars.enums;

public enum CarType {

    M("Mini"),
    E("Economy"),
    C("Compact"),
    I("Intermediate"),
    S("Standard"),
    F("Full size"),
    P("Premium"),
    L("Luxury"),
    X("Special");

    private String value;
    
    CarType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}