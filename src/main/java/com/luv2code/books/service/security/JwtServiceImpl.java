package com.luv2code.books.service.security;

import org.springframework.security.core.userdetails.UserDetails;

import java.util.Map;

public class JwtServiceImpl implements JwtService {
    @Override
    public String extractUsername(String token) {
        return "";
    }

    @Override
    public boolean isTokenValid(String token, UserDetails userDetails) {
        return false;
    }

    @Override
    public String generateToken(Map<String, Object> claims, UserDetails userDetails) {
        return "";
    }
}
