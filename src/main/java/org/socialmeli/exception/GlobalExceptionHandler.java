package org.socialmeli.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.AccessLevel;
import lombok.experimental.FieldDefaults;
import org.socialmeli.dto.response.ExceptionDto;
import org.socialmeli.dto.response.NotValidExceptionDto;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.support.WebExchangeBindException;
import org.springframework.web.method.annotation.HandlerMethodValidationException;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

import java.util.List;

@ControllerAdvice(annotations = RestController.class)
@FieldDefaults(level = AccessLevel.PRIVATE)
public class GlobalExceptionHandler {

    @ExceptionHandler(BadRequestException.class)
    public ResponseEntity<?> badRequest(BadRequestException e){
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<?> notFound(NotFoundException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto,  HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<?> typeMismatchException(MethodArgumentTypeMismatchException e) {
        ExceptionDto exceptionDto = new ExceptionDto(e.getMessage());
        return new ResponseEntity<>(exceptionDto,  HttpStatus.BAD_REQUEST);
    }
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<?> handlerMethodValidationException(ConstraintViolationException e) {

        ExceptionDto exceptionDto = new ExceptionDto(e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionDto,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<?> handlerMethodValidationException(HttpMessageNotReadableException e) {

        ExceptionDto exceptionDto = new ExceptionDto(e.getLocalizedMessage());
        return new ResponseEntity<>(exceptionDto,  HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handlerMethodValidationException(MethodArgumentNotValidException e) {

        //List<ExceptionDto> exceptionsDto = e.getBindingResult().getAllErrors().stream().map(error -> new ExceptionDto( error.getDefaultMessage())).toList();

        //ExceptionDto exceptionDto = new ExceptionDto(e.getBindingResult().getFieldError().getDefaultMessage());
        ExceptionDto exceptionDto = new ExceptionDto(e.getDetailMessageArguments()[1].toString());


        return new ResponseEntity<>(exceptionDto,  HttpStatus.BAD_REQUEST);
    }
}
