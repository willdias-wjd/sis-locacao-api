package com.sislocacao.adapter.exception.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = true)
public class ValidationError extends StandardError {
    private List<ValidationFieldError> errors = new ArrayList<>();
}

