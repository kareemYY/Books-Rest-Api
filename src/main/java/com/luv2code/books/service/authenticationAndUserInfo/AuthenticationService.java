package com.luv2code.books.service.authenticationAndUserInfo;

import com.luv2code.books.dto.request.AuthenticationRequest;
import com.luv2code.books.dto.request.RegisterRequest;
import com.luv2code.books.dto.response.AuthenticationResponse;

public interface AuthenticationService {
    void register(com.luv2code.books.dto.request.RegisterRequest input)throws Exception;

    AuthenticationResponse login(AuthenticationRequest request);
}
