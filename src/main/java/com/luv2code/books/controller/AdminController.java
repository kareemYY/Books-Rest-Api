package com.luv2code.books.controller;


import com.luv2code.books.dto.response.UserResponse;
import com.luv2code.books.service.books.AdminService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.constraints.Min;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/admin")
@Tag(name = "Admin REST API Endpoints",description = "Operation related to a admin")
public class AdminController {


    private final AdminService adminService;

    public AdminController(AdminService adminService) {
        this.adminService = adminService;
    }

    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Get All user",description = "Retrieve a list of all user in the system")
    public List<UserResponse> getAllUsers(){return adminService.getAllUsers();}



    @PutMapping("/{userId}/promotetoadmin")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Promote To Admin",description = "Give user role admin")
    public UserResponse promoteToAdmin(@PathVariable @Min(1) long userId){
        return adminService.promoteToAdmin(userId);
    }


    @PutMapping("/{userId}/promotetoauthor")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "Promote To Author",description = "Give user role author")
    public UserResponse promoteToAuthor(@PathVariable @Min(1) long userId){
        return adminService.promoteToAuthor(userId);
    }

    @Operation(summary = "Deleting user",description = "Delete a non-admin user from the system")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable @Min(1) long userId){
        adminService.deleteUser(userId);
    }
}
