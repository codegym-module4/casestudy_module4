package com.codegym.casestudy_module4.ulti;

import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    public static Map<String, String> getErrorMes(BindingResult bindingResult) {
        Map<String, String> list = new HashMap<>();
        bindingResult.getFieldErrors().stream().forEach(err -> list.put(err.getField(), err.getDefaultMessage()));
        return list;
    }


//        public static Map<String, String> getErrorMes(BindingResult bindingResult) {
//            Map<String, String> list = new HashMap<>();
//            bindingResult.getFieldErrors().forEach(err -> list.put(err.getField(), err.getDefaultMessage()));
//            return list;
//        }


//        return bindingResult.getAllErrors()
//                .stream()
//                .map(error -> {
//                    var defaultMessage = error.getDefaultMessage();
//                    if (error instanceof FieldError) {
//                        assert defaultMessage != null;
//                        return String.format(defaultMessage);
//                    } else {
//                        return defaultMessage;
//                    }
//                })
//                .collect(Collectors.toList());
//
//    }

}
