package com.luv2code.books.controller;


import com.luv2code.books.dto.request.AuthenticationRequest;
import com.luv2code.books.dto.request.RegisterRequest;
import com.luv2code.books.dto.response.AuthenticationResponse;
import com.luv2code.books.service.authenticationAndUserInfo.AuthenticationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@Tag(name = "Authentication REST API Endpoint",description = "Operation related to register & login")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }


    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/register")
    @Operation(summary = "Register a user ", description = "Create new user in database")
    public void register (@Valid @RequestBody RegisterRequest registerRequest) throws Exception {
        authenticationService.register(registerRequest);
    }


    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Login a user",description = "submit email & password to authenticate user")
    @PostMapping("/login")
    public AuthenticationResponse login(@Valid @RequestBody AuthenticationRequest authenticationRequest) {
        return authenticationService.login(authenticationRequest);
    }












}
