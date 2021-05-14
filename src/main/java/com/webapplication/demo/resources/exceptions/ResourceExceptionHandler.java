package com.webapplication.demo.resources.exceptions;

import com.sun.jdi.ObjectCollectedException;
import com.webapplication.demo.services.exceptions.ObjectNotFoundException;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 *
 * @author CarlosMacaneta
 */
@ControllerAdvice
public class ResourceExceptionHandler {
    
    /*
    Tratamento padra de exception
    */
    @ExceptionHandler(ObjectCollectedException.class)
    public ResponseEntity<StandardError> objectNotFount(ObjectNotFoundException exception, HttpServletRequest request) {
        StandardError err = new StandardError(HttpStatus.NOT_FOUND.value(), exception.getMessage(), System.currentTimeMillis());
        
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }
}
