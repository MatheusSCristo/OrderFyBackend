package com.orderfy.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ApiExceptionResponse extends RuntimeException {
    private String message;
    private int status;
    private LocalDateTime timestamp=LocalDateTime.now();

    public ApiExceptionResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }


}



