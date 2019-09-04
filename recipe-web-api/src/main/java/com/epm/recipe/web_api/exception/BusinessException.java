package com.epm.recipe.web_api.exception;

public abstract class BusinessException extends RuntimeException {

    public BusinessException() {
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public abstract String name();

    public abstract String description();

    @Override
    public String getMessage() {
        return description();
    }

    @Override
    public String getLocalizedMessage() {
        return description();
    }
}
