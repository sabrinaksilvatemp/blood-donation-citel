package com.kappann.citel.domain.enums;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import java.util.Arrays;

public enum Gender {
    MALE("Masculino"),
    FEMALE("Feminino");

    private final String description;

    Gender(String description) {
        this.description = description;
    }

    @JsonValue
    public String getDescription() {
        return description;
    }

    @JsonCreator
    public static Gender forValue(String value) {
        for (Gender gender : Gender.values()) {
            if (gender.description.equalsIgnoreCase(value)) {
                return gender;
            }
        }
        throw new IllegalArgumentException("Invalid gender value: " + value + " - Valid values are: " + Arrays.stream(Gender.values()).map(Gender::getDescription));
    }
}