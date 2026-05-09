package com.project.clinic.exceptions;

import static java.lang.String.format;

public abstract class NotFoundException extends RuntimeException {

    public NotFoundException(String message) {
        super(message);
    }

    public static class EmployeeNotFoundException extends NotFoundException {
//        public EmployeeNotFoundException(Long id) {
//            super(format("Could not find employee with id [=%s]", id));
//        }

        public EmployeeNotFoundException(String id) {
            super(String.format("Could not find employee with id [=%s]", id));
        }
    }
}