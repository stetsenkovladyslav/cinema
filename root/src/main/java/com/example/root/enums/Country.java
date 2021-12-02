package com.example.root.enums;

import lombok.Getter;

@Getter
public enum Country implements BaseEnum{
    UNITED_STATES("UNITED_STATES"),
    GERMANY("GERMANY"),
    ENGLAND("ENGLAND"),
    UKRAINE("UKRAINE"),
    SPAIN("SPAIN");

    private String value;

    Country (String value) {
        this.value = value;
    }
}
