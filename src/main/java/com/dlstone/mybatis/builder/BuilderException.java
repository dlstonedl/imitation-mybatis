package com.dlstone.mybatis.builder;

public class BuilderException extends RuntimeException {

    public BuilderException(String message) {
        super(message);
    }

    public BuilderException(String message, Throwable cause) {
        super(message, cause);
    }
}
