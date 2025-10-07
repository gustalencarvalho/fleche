package com.api.fleche.enums;

public enum Gender {
    HETEROSSEXUAL("HETEROSSEXUAL"),
    TRANSSEXUAL("TRANSSEXUAL"),
    HOMOSSEXUAL("HOMOSSEXUAL"),
    BISSEXUAL("BISSEXUAL");

    private String gender;

    Gender(String gender) {
        this.gender = gender;
    }

    public String getGender() {
        return gender;
    }
}
