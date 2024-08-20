package com.bside.test.error;

public class AlumnoNotFoundException extends RuntimeException {
    /**
     * 
     */
    private static final long serialVersionUID = -1568373076869564459L;

    public AlumnoNotFoundException(String message) {
        super(message);
    }
}