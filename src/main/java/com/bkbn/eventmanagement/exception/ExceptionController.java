package com.bkbn.eventmanagement.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;

@ControllerAdvice
@ResponseBody
public class ExceptionController {

    @ExceptionHandler(NotFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ExceptionResponse handleNotFoundException(NotFoundException exception) {
        return ExceptionResponse.builder().message(exception.getMessage()).timestamp(new Date()).build();
    }
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleBadRequestException(BadRequestException exception) {
        return ExceptionResponse.builder().message(exception.getMessage()).timestamp(new Date()).build();
    }

    @ExceptionHandler(ConnectionException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionResponse handleConnectionException(ConnectionException exception) {
        return ExceptionResponse.builder().message(exception.getMessage()).timestamp(new Date()).build();
    }

    @ExceptionHandler(SocketException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionResponse handleSocketException(SocketException exception) {
        return ExceptionResponse.builder().message(exception.getMessage()+" is not available").timestamp(new Date()).build();
    }
    @ExceptionHandler(UnknownHostException.class)
    @ResponseStatus(value = HttpStatus.SERVICE_UNAVAILABLE)
    public ExceptionResponse handleUnknownHostException(UnknownHostException exception) {
        return ExceptionResponse.builder().message(exception.getMessage()+" is not available").timestamp(new Date()).build();
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ExceptionResponse handleMethodArgumentNotValidException(MethodArgumentNotValidException exception) {
        return ExceptionResponse.builder()
                .message(exception.getBindingResult().getAllErrors().get(0).getDefaultMessage())
                .timestamp(new Date()).build();
    }
}
