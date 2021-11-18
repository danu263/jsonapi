package com.example.blogpostapi.exception;

import com.example.blogpostapi.utils.GenericError;

import java.io.Serializable;

public class BlogPostApiException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = -123456789L;
    private final String code;

    public BlogPostApiException(GenericError error, String message, Throwable cause) {
        super(message, cause);
        this.code = error.getCode();
    }

    public BlogPostApiException(GenericError error, String message) {
        super(message);
        this.code = error.getCode();
    }

    public BlogPostApiException(GenericError error) {
        super(error.getMessage());
        this.code = error.getCode();
    }

    public String getCode() {
        return code;
    }
}
