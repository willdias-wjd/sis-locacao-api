package com.sislocacao.adapter.exception.handler;

import com.sislocacao.adapter.exception.dto.StandardError;
import com.sislocacao.adapter.exception.dto.ValidationError;
import com.sislocacao.adapter.exception.dto.ValidationFieldError;
import com.sislocacao.core.exception.BusinessException;
import com.sislocacao.core.exception.ResourceNotFoundException;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.http.converter.HttpMessageNotReadableException;

import jakarta.validation.ConstraintViolationException;

import java.time.Instant;
import java.util.List;

@ControllerAdvice
public class HandlerException {

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<StandardError> businessException(BusinessException e, HttpServletRequest req){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Business Exception");
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<StandardError> resourceNotFoundException(ResourceNotFoundException e, HttpServletRequest req){
        StandardError err = new StandardError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.NOT_FOUND.value());
        err.setError("Resource Not Found");
        err.setMessage(e.getMessage());
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(err);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ValidationError> methodArgumentNotValidException(MethodArgumentNotValidException e, HttpServletRequest req) {
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Bad Request");
        err.setMessage("Erro de validação nos campos enviados");
        err.setPath(req.getRequestURI());

        List<ValidationFieldError> errors = e.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(fe -> new ValidationFieldError(fe.getField(), fe.getDefaultMessage()))
                .toList();
        err.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<ValidationError> constraintViolationException(ConstraintViolationException e, HttpServletRequest req) {
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Bad Request");
        err.setMessage("Erro de validação nos campos enviados");
        err.setPath(req.getRequestURI());

        List<ValidationFieldError> errors = e.getConstraintViolations()
                .stream()
                .map(v -> new ValidationFieldError(extractLastPathNode(v.getPropertyPath().toString()), v.getMessage()))
                .toList();
        err.setErrors(errors);

        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<ValidationError> httpMessageNotReadableException(HttpMessageNotReadableException e, HttpServletRequest req) {
        ValidationError err = new ValidationError();
        err.setTimestamp(Instant.now());
        err.setStatus(HttpStatus.BAD_REQUEST.value());
        err.setError("Bad Request");
        err.setMessage("Corpo da requisição inválido");
        err.setPath(req.getRequestURI());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(err);
    }

    private String extractLastPathNode(String path) {
        if (path == null || path.isBlank()) return path;
        int idx = path.lastIndexOf('.');
        return idx >= 0 ? path.substring(idx + 1) : path;
    }
}
