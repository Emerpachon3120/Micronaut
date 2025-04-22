package com.coom.ath.constants;

public enum HeadersEnum {
    CONTENT_TYPE("Content-Type");

    private final String value;

    HeadersEnum(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
