package com.codegym.casestudy_module4.ulti;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.List;
import java.util.stream.Collectors;

public class ValidationMessage {
    public static List<String> getErrorMessages(BindingResult bindingResult) {
        return bindingResult.getAllErrors()
                .stream()
                .map(error -> {
                    var defaultMessage = error.getDefaultMessage();
                    if (error instanceof FieldError) {
                        assert defaultMessage != null;
                        return String.format(defaultMessage);
                    } else {
                        return defaultMessage;
                    }
                })
                .collect(Collectors.toList());

    }
}
