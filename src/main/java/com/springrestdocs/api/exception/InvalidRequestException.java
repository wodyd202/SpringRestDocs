package com.springrestdocs.api.exception;

import lombok.Getter;
import org.springframework.validation.ObjectError;

import java.util.List;
import java.util.stream.Collectors;

import static java.util.stream.Collectors.toList;

@Getter
public class InvalidRequestException extends RuntimeException {
    private final List<String> errorMessages;
    public InvalidRequestException(List<ObjectError> allErrors) {
        errorMessages = allErrors.stream().map(ObjectError::getDefaultMessage).collect(toList());
    }
}
