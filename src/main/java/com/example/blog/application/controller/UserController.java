package com.example.blog.application.controller;

import com.example.blog.application.dto.RegisterRequest;
import com.example.blog.application.dto.UserDto;
import com.example.blog.application.model.Users;
import com.example.blog.application.response.ApiResponse;
import com.example.blog.application.service.user.UsersService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_ERROR_MESSAGE;
import static com.example.blog.application.utils.exceptions.ApiResponseUtils.REQUEST_SUCCESS_MESSAGE;
import static org.springframework.http.HttpStatus.NOT_FOUND;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UsersService usersService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> RegisterUser(@RequestBody RegisterRequest request) {
        try {
            Users users = usersService.createUser(request);
            UserDto createdUser = usersService.convertUserToDto(users);
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, createdUser));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/all")
    public ResponseEntity<ApiResponse> getAllUsers() {
        try {
            List<UserDto> users = usersService.getAllUsers();
            if (users.isEmpty()) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "No users found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, users));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<ApiResponse> getUserById(@PathVariable Long id) {
        try {
            UserDto user = usersService.getUserById(id);
            if (user == null) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "User not found"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, user));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<ApiResponse> updateUser(@PathVariable Long id, @RequestBody UserDto userDto) {
        try {
            UserDto updated = usersService.updateUser(id, userDto);
            if (updated == null) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "User not found for update"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, updated));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<ApiResponse> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = usersService.deleteUser(id);
            if (!deleted) {
                return ResponseEntity.status(NOT_FOUND).body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "User not found for deletion"));
            }
            return ResponseEntity.ok().body(new ApiResponse(REQUEST_SUCCESS_MESSAGE, "User deleted successfully"));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ApiResponse(REQUEST_ERROR_MESSAGE, e.getMessage()));
        }
    }
}
