package com.arens.userportal.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

@ControllerAdvice
@RestController
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public final ResponseEntity<ExceptionResponse> handleUserNotFoundExceptions(Exception ex, WebRequest request) {

            ExceptionResponse exceptionResponse = new ExceptionResponse(
                                    new Date(),
                                    ex.getMessage(),
                                    request.getDescription(false)
            );




            return new ResponseEntity<>(exceptionResponse, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(value = {BadRequestException.class, DataIntegrityViolationException.class})
    public final ResponseEntity<ExceptionResponse> handleBadRequestException(Exception ex, WebRequest request) {
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                                                        new Date(),
                                                        ex.getMessage(),
                                                        request.getDescription(false)
        );

        return new ResponseEntity<>(exceptionResponse, HttpStatus.BAD_REQUEST);
    }


    @ExceptionHandler(value = {ConflictException.class})
    public final ResponseEntity<ExceptionResponse> handleConflict(Exception ex, WebRequest req) {

        String message = ex.getMessage() + " *** " + ExceptionUtils.getRootCauseMessage(ex);
        ExceptionResponse exceptionResponse = new ExceptionResponse(
                new Date(),
                message,
                req.getDescription(false)
        );
        /*
        ResponseEntity<ExceptionResponse> responseEntity =
                new ResponseEntity<ExceptionResponse>(exceptionResponse,
                        HttpStatus.BAD_REQUEST);
        */
        return new ResponseEntity<>(exceptionResponse,
                HttpStatus.CONFLICT);

    }

    @Override
    protected ResponseEntity<Object> handleHttpMessageNotReadable(
            HttpMessageNotReadableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = ex.getMessage() == null? ex.getClass().getSimpleName() : ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                                                     message,
                                                     request.getDescription(false));
        return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {

        String message = ex.getMessage() == null? ex.getClass().getSimpleName() : ex.getMessage();
        ExceptionResponse exceptionResponse = new ExceptionResponse(new Date(),
                message,
                request.getDescription(false));
        return handleExceptionInternal(ex, exceptionResponse, headers, HttpStatus.BAD_REQUEST, request);
    }

}
