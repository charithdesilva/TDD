package com.aconex.codechallenge.exceptions;

public class AconexException extends Exception {

    private static final long serialVersionUID = 5280340121557817062L;

    public AconexException() {
    }

    public AconexException(String message) {
	super(message);
    }

    public AconexException(Throwable cause) {
	super(cause);
    }

    public AconexException(String message, Throwable cause) {
	super(message, cause);
    }
}