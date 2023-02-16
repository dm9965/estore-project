package com.estore.api.estoreapi.enums;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonValue;

@JsonFormat(shape = JsonFormat.Shape.STRING)
public enum Sizing {
    MALE("MALE"),
    FEMALE("FEMALE");

    private final String unit;

    Sizing(String unit) {
        this.unit = unit;
    }

    @JsonValue
    public String getSizing() {
        return unit;
    }
}
