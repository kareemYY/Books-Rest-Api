package com.luv2code.books.service.books;


import com.luv2code.books.dto.response.UserResponse;

import java.util.List;

public interface AdminService {

    List<UserResponse> getAllUsers();

    UserResponse promoteToAdmin(long id);

    UserResponse promoteToAuthor(long id);

    void deleteUser(long id);
}
