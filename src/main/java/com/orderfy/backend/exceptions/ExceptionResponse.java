package com.orderfy.backend.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {
        private String message;
        private int status;
        private LocalDateTime timestamp;
}
