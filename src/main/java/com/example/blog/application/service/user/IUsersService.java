package com.example.blog.application.service.user;

import com.example.blog.application.dto.LoginRequest;
import com.example.blog.application.dto.LoginResponse;
import com.example.blog.application.dto.RegisterRequest;
import com.example.blog.application.dto.UserDto;
import com.example.blog.application.model.Users;

import java.util.List;

public interface IUsersService {
    LoginResponse logInUser(LoginRequest request);
    Users createUser(RegisterRequest request);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
}
