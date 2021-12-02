package com.example.root.enums;

import lombok.Getter;

@Getter
public enum Genre implements  BaseEnum {
    ACTION("ACTION"),
    HORROR("HORROR"),
    DRAMA("DRAMA"),
    COMEDY("COMEDY"),
    THRILLER("THRILLER");

    private String value;

    Genre (String value) {
        this.value = value;
    }
}
