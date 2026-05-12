package com.luv2code.books.service.authenticationAndUserInfo;

import com.luv2code.books.dto.request.PasswordRequest;
import com.luv2code.books.dto.response.UserResponse;
import com.luv2code.books.entity.Authority;
import com.luv2code.books.entity.User;
import com.luv2code.books.repository.UserRepository;
import com.luv2code.books.util.FindAuthenticatedUser;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UserServiceImpl implements UserService {


    private final UserRepository userRepository;

    private final FindAuthenticatedUser findAuthenticatedUser;

    private final PasswordEncoder passwordEncoder;

    public UserServiceImpl(UserRepository userRepository,
                           FindAuthenticatedUser findAuthenticatedUser,
                           PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.findAuthenticatedUser = findAuthenticatedUser;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional(readOnly = true)
    @Override
    public UserResponse getUserInfo() {
        User user =findAuthenticatedUser.getAuthenticatedUser();
        return new UserResponse(user.getId(),
                user.getFirstName()+" "+user.getLastName(),
                user.getEmail(),
                user.getAuthorities().stream().map(auth -> (Authority)  auth).toList());
    }

    @Transactional
    @Override
    public void updatePassword(PasswordRequest passwordRequest) {
        User user =findAuthenticatedUser.getAuthenticatedUser();
        if(!isOldPasswordCorrect(user.getPassword(),passwordRequest.getOldPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Current password is incorrect.");
        }
        if(!isNewPasswordConfirmed(passwordRequest.getNewPassword(),passwordRequest.getNewPasswordConfirm())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"New Password do not match.");
        }
        if(!isNewPasswordDifferent(passwordRequest.getOldPassword(),passwordRequest.getNewPassword())){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"New Password must be different ");
        }
        user.setPassword(passwordEncoder.encode(passwordRequest.getNewPassword()));
        userRepository.save(user);
    }

    private boolean isOldPasswordCorrect(String currentPassword, String oldPassword) {
        return passwordEncoder.matches(oldPassword,currentPassword);
    }

    private boolean isNewPasswordConfirmed(String newPassword, String newPasswordConfirmed) {
        return newPassword.equals(newPasswordConfirmed);
    }

    private boolean isNewPasswordDifferent(String oldPassword, String newPassword ) {
        return !oldPassword.equals(newPassword);
    }



    @Transactional
    @Override
    public void deleteUser() {
        User user =findAuthenticatedUser.getAuthenticatedUser();
        if(isLAstAdmin(user)){
            throw new ResponseStatusException(HttpStatus.FORBIDDEN,"Admin Cannot delete it self");
        }
        userRepository.delete(user);

    }

    private boolean isLAstAdmin(User user) {
        boolean isAdmin = user.getAuthorities().stream().
                anyMatch(authority ->
                        "ROLE_ADMIN".equals(authority.getAuthority()));
        if (isAdmin) {
            long adminCount= userRepository.countAdmin();
            return adminCount <= 1;
        }
        return false;
    }
}
