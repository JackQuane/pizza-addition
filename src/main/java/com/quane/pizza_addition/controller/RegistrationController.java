package com.quane.pizza_addition.controller;

import com.quane.pizza_addition.model.RegistrationResponse;
import com.quane.pizza_addition.service.RegistrationService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/registration")
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @PostMapping
    @ApiOperation("register a new user of the API")
    public ResponseEntity<RegistrationResponse> register() {
        return ResponseEntity.status(HttpStatus.OK).body(registrationService.register());
    }

}
