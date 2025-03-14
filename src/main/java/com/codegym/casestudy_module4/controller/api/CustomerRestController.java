package com.codegym.casestudy_module4.controller.api;

import com.codegym.casestudy_module4.entity.Customer;
import com.codegym.casestudy_module4.entity.Medicine;
import com.codegym.casestudy_module4.service.ICustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/customers")
public class CustomerRestController {

    @Autowired
    private ICustomerService customerService;

    @PostMapping
    public ResponseEntity<?> createCustomer(
            @Validated @ModelAttribute("customer") Customer customer,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            // Trả về JSON đúng format
            Map<String, Object> errors = new HashMap<>();
            errors.put("errors", true);
            errors.put("message", "Validation failed");
            Map<String, String> validations = new HashMap<>();
            bindingResult.getFieldErrors().forEach(error -> {
                validations.put(error.getField(), error.getDefaultMessage());
            });
            errors.put("validator", validations);

            return ResponseEntity.badRequest()
                    .contentType(MediaType.APPLICATION_JSON) // Chắc chắn trả về JSON
                    .body(errors);
        }
        Customer res = customerService.updateOrCreate(customer);

        return new ResponseEntity<>(res, HttpStatus.CREATED);
    }
}
