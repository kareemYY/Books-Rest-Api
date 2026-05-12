package com.luv2code.books.service.authenticationAndUserInfo;

import com.luv2code.books.dto.request.AuthenticationRequest;
import com.luv2code.books.dto.request.RegisterRequest;
import com.luv2code.books.dto.response.AuthenticationResponse;
import com.luv2code.books.entity.Authority;
import com.luv2code.books.entity.User;
import com.luv2code.books.repository.UserRepository;
import com.luv2code.books.service.security.JwtService;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final JwtService jwtService;

    public AuthenticationServiceImpl(UserRepository userRepository,
                                     PasswordEncoder passwordEncoder,
                                     AuthenticationManager authenticationManager,
                                     JwtService jwtService) {
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional
    public void register(RegisterRequest input)throws Exception {
        if(isEmailTaken(input.getEmail())){
            throw new Exception("Email already taken");
        }
        User user =builNewUser(input);
        userRepository.save(user);
    }

    @Override
    @Transactional(readOnly = true)
    public AuthenticationResponse login(AuthenticationRequest request) {

        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        User user=userRepository.findByEmail(request.getEmail()).
                orElseThrow(()->new IllegalStateException("Invalid email or password"));
        String jwtToken=jwtService.generateToken(new HashMap<>(),user);


        return new AuthenticationResponse(jwtToken) ;
    }

    private boolean isEmailTaken(String email) {
        return userRepository.findByEmail(email).isPresent();
    }

    private User builNewUser(RegisterRequest input) {
        User user = new User();
        user.setId(0);
        user.setFirstName(input.getFirstName());
        user.setLastName(input.getLastName());
        user.setEmail(input.getEmail());
        user.setPassword(passwordEncoder.encode(input.getPassword()));
        user.setAuthorities(initAuthorities());
        return user;
    }

    private List<Authority> initAuthorities() {
        boolean isFirstUser = userRepository.count() == 0;
        List<Authority> authorities = new ArrayList<>();
        authorities.add(new Authority("ROLE_USER"));
        if (isFirstUser) {
            authorities.add(new Authority("ROLE_AUTHOR"));
            authorities.add(new Authority("ROLE_ADMIN"));
        }
        return authorities;
    }
}
