package com.example.blogpostapi.utils.impl;

import com.example.blogpostapi.utils.GenericError;

public enum GenericErrorEnum implements GenericError {

    ER0000("GEN00000", "Unknown error encountered"),
    ER0001("GEN00001", "Tags parameter is required"),
    ER0002("GEN00002", "sortBy parameter is invalid"),
    ER0003("GEN00003", "direction parameter is invalid");

    GenericErrorEnum(String code, String message) {
        this.code = code;
        this.message = message;
    }

    private final String code;
    private final String message;

    @Override
    public String getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }
}
