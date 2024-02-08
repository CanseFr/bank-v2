package org.bank.bankv2.handlers;

import jakarta.persistence.EntityNotFoundException;
import org.bank.bankv2.exceptions.OperationNonPermittedException;
import org.bank.bankv2.exceptions.customs.InsufficientFinancialFunds;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InsufficientFinancialFunds.class)
    public ResponseEntity<ExceptionRepresentation> handlerException(InsufficientFinancialFunds exception){

        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Vous n'avez pas les fonds necessaire")
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<ExceptionRepresentation> handlerException(EntityNotFoundException exception){

        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getMessage())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(representation);
    }

    @ExceptionHandler(OperationNonPermittedException.class)
    public ResponseEntity<ExceptionRepresentation> handlerException(OperationNonPermittedException exception){

        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage(exception.getErrorMsg())
                .build();

        return ResponseEntity
                .status(HttpStatus.NOT_ACCEPTABLE)
                .body(representation);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    public ResponseEntity<ExceptionRepresentation> handlerException(DataIntegrityViolationException exception){

        ExceptionRepresentation representation = ExceptionRepresentation.builder()
                .errorMessage("Cette donnée est deja existante")
                .build();

        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(representation);
    }

}
