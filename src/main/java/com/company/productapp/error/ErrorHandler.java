package com.company.productapp.error;

import com.company.productapp.error.model.ErrorResponseDto;
import com.company.productapp.error.model.ProductNotFoundException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ErrorHandler extends ResponseEntityExceptionHandler {

    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ExceptionHandler(ProductNotFoundException.class)
    public ErrorResponseDto handleRuntimeException(ProductNotFoundException ex) {
        ErrorResponseDto errorResponseDto = new ErrorResponseDto();
        errorResponseDto.setCode(HttpStatus.BAD_REQUEST.value());
        errorResponseDto.setMessage(ex.getMessage());
        return errorResponseDto;
    }

//    @ExceptionHandler(ProductNotFoundException.class)
//    public ResponseEntity handleRuntimeException(ProductNotFoundException ex) {
//        return ResponseEntity.badRequest().body(ex.getMessage());
//    }

    @Override
        protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatus status,
            WebRequest request) {
        return ResponseEntity
                .badRequest()
                .body(new ErrorResponseDto(HttpStatus.BAD_REQUEST.value(), ex.getMessage()));
    }

}
