package com.example.management.enums;

public enum EmployeeExceptionEnum {

    USER_NOT_FOUND("601","User not found ");

    private final String errorCode;
    private final String description;
    EmployeeExceptionEnum(String errorCode, String description) {
        this.errorCode = errorCode;
        this.description = description;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public String getDescription() {
        return description;
    }
}
