package com.orderfy.auth_service.core.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class ExceptionResponse {
        private int statusCode;
        private String message;
        private long timestamp;

}
