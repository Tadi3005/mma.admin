package org.helmo.mma.admin.domains.exception;

/**
 * Exception thrown when a conversion fails.
 */
public class ConversionException extends RuntimeException {
    /**
     * Create a new ConversionException with a message.
     * @param message the message of the exception
     */
    public ConversionException(String message) {
        super(message);
    }
}
