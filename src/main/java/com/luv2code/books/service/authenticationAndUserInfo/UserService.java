package com.luv2code.books.service.authenticationAndUserInfo;


import com.luv2code.books.dto.request.PasswordRequest;
import com.luv2code.books.dto.response.UserResponse;

public interface UserService {

    UserResponse getUserInfo();

    void deleteUser();

    void updatePassword(PasswordRequest passwordRequest);
}
