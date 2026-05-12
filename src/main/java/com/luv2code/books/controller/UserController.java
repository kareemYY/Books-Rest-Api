package com.luv2code.books.controller;


import com.luv2code.books.dto.request.PasswordRequest;
import com.luv2code.books.dto.response.UserResponse;
import com.luv2code.books.service.authenticationAndUserInfo.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

@Tag(name = "User REST API Endpoint ",description = "Operation related to info of current user")
@RestController
@RequestMapping("/api/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(summary = "User Information", description = "Get current user info")
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/info")
    public UserResponse getUserInfo() {
        return userService.getUserInfo();
    }

    @Operation(summary = "Delete user",description = "Delete current user account")
    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping
    public void deleteUser() {
        userService.deleteUser();
    }


    @Operation(summary = "Password update", description = "Changing user password after verification")
    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/password")
    public void passwordUpdate(@Valid @RequestBody PasswordRequest passwordRequest)throws Exception{
        userService.updatePassword(passwordRequest);
    }
}
