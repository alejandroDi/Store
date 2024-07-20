package com.example.demo.exception;

import java.time.LocalDateTime;

public record CustomErrorResponse (
        LocalDateTime timestamp,
        String message,
        String path
){

}
