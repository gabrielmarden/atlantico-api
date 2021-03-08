package br.com.testeatlantico.api.exceptionshandler;


import br.com.testeatlantico.api.dto.ErrorDTO;
import br.com.testeatlantico.api.exceptions.ResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDate;

@ControllerAdvice
public class Handler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> ResourceNotFoundHandler(ResourceNotFoundException e){
        ErrorDTO error = new ErrorDTO("Resource not found exception",e.getMessage(), LocalDate.now());
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
