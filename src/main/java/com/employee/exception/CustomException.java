package com.employee.exception;

/**
 * Exception for custom errors.
 */
public class CustomException extends RuntimeException {
    
    public CustomException(String message) {
        super(message);
    }
    
    public static class ConnectionException extends CustomException {
        public ConnectionException(String message) {
            super(message);
        }
    }
    
    public static class IdAlreadyAvailableException extends CustomException {
        public IdAlreadyAvailableException(String message) {
            super(message);
        }
    }
    
    public static class IdNotFoundException extends CustomException { 
        public IdNotFoundException(String message) {
            super(message);
        }
    }
    
    public static class AccessFailedException extends CustomException {
        public AccessFailedException(String message) {
            super(message);
        }
    }
    
    public static class DateNotValidException extends CustomException {
        public DateNotValidException(String message) {
        super(message);
        }
    }
    
    public static class NoRecordFoundException extends CustomException {
        public NoRecordFoundException(String message) {
        super(message);
        }
    }
}
