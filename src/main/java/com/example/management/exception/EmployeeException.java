package com.example.management.exception;

import com.example.management.enums.EmployeeExceptionEnum;

public class EmployeeException extends RuntimeException {
    private static final long serialVersionUID = 5152631697512284456L;
    private String code;

    public EmployeeException() {
    }

    public EmployeeException(String message) {
        super(message);
    }

    public EmployeeException(String code, String message) {
        super(message);
        this.code = code;
    }

    public EmployeeException(EmployeeExceptionEnum employeeExceptionEnum) {
        super(employeeExceptionEnum.getDescription());
        this.code = employeeExceptionEnum.getErrorCode();
    }

    public String getCode() {
        return code;
    }
}

