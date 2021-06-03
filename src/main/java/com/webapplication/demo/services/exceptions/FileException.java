package com.webapplication.demo.services.exceptions;

/**
 *
 * @author CarlosMacaneta
 */
public class FileException extends RuntimeException {

    public FileException(String message) {
        super(message);
    }

    public FileException(String message, Throwable cause) {
        super(message, cause);
    }
    
}
