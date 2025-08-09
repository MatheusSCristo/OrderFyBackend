package com.orderfy.backend.exceptions;

import com.orderfy.backend.exceptions.customer.ExceptionResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalHandlerException {

    private static final Logger logger = LoggerFactory.getLogger(GlobalHandlerException.class);

    private void handledExceptionLog(Exception ex) {
        logger.warn("Exceção de API tratada: {}", ex.getMessage());
    }

    @ExceptionHandler(ApiException.class)
    public ResponseEntity<Object> handleApiException(ApiException ex) {
        ExceptionResponse response = new ExceptionResponse(ex.getMessage(), ex.getStatus(), ex.getTimestamp());
        handledExceptionLog(ex);
        return new ResponseEntity<>(response, HttpStatus.valueOf(ex.getStatus()));
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseEntity<ExceptionResponse> handleBadCredentialsException(BadCredentialsException ex) {
        ExceptionResponse response = new ExceptionResponse(
                "Credenciais inválidas",
                HttpStatus.BAD_REQUEST.value(),
                LocalDateTime.now()
        );

        handledExceptionLog(ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionResponse> handleGenericException(Exception ex) {
        ExceptionResponse response = new ExceptionResponse(
                "Erro interno no servidor. Tente novamente.",
                HttpStatus.INTERNAL_SERVER_ERROR.value(),
                LocalDateTime.now()
        );

        logger.error("Ocorreu uma exceção não tratada: ", ex);
        return new ResponseEntity<>(response, HttpStatus.INTERNAL_SERVER_ERROR);
    }


}
