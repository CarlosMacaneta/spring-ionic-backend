package com.webapplication.demo.resources.exceptions;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author CarlosMacaneta
 */
public class ValidationError extends StandardError {
    
    private List<FieldMessage> errors = new ArrayList<>();

    public ValidationError(Long timestamp, Integer status, String error, String message, String path) {
        super(timestamp, status, error, message, path);
    } 

    public List<FieldMessage> getErrors() {
        return errors;
    }
    
    public void addError(String fieldName, String message) {
        errors.add(new FieldMessage(fieldName, message));
    }
}
