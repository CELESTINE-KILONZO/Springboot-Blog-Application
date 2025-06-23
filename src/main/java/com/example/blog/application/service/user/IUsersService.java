package com.example.blog.application.service.user;

import com.example.blog.application.dto.UserDto;

import java.util.List;

public interface IUsersService {
    UserDto createUser(UserDto userDto);
    List<UserDto> getAllUsers();
    UserDto getUserById(Long id);
    UserDto updateUser(Long id, UserDto userDto);
    boolean deleteUser(Long id);
}
