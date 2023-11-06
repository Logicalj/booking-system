package com.logicalj.bms.movieservice.controller;

import com.logicalj.bms.movieservice.model.ErrorResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

import static com.logicalj.bms.movieservice.constants.ApplicationConstants.API_FAILURE;


@Component
@ControllerAdvice
public class BaseController {

  @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
  @ExceptionHandler(HttpMediaTypeNotSupportedException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptionsUmt(
          HttpMediaTypeNotSupportedException ex) {
    ErrorResponse errorResponse;
    StringBuilder errMsg = new StringBuilder();
    errMsg.append(ex.getMessage());
    errorResponse =
            ErrorResponse.builder().errorMessage(errMsg.toString()).status(API_FAILURE).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.UNSUPPORTED_MEDIA_TYPE);
  }

  @ResponseStatus(HttpStatus.BAD_REQUEST)
  @ExceptionHandler(MethodArgumentNotValidException.class)
  public ResponseEntity<ErrorResponse> handleValidationExceptions(
          MethodArgumentNotValidException ex) {
    ErrorResponse errorResponse;
    StringBuilder errMsg = new StringBuilder();
    ex.getBindingResult()
            .getAllErrors()
            .forEach(error ->
                    errMsg.append(error.getDefaultMessage()).append(". ").toString().trim()
            );
    errorResponse =
            ErrorResponse.builder().errorMessage(errMsg.toString()).status(API_FAILURE).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

  @ExceptionHandler(DataIntegrityViolationException.class)
  public ResponseEntity<ErrorResponse> handleRepositoryExceptions(
          DataIntegrityViolationException ex) {
    ErrorResponse errorResponse;
    StringBuilder errMsg = new StringBuilder();
    errMsg.append(ex.getMessage());
    errorResponse =
            ErrorResponse.builder().errorMessage(errMsg.toString()).status(API_FAILURE).build();
    return new ResponseEntity<>(errorResponse, HttpStatus.BAD_REQUEST);
  }

}
