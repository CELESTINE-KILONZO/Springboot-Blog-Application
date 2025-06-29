package com.example.blog.application.service.user;

import com.example.blog.application.dto.LoginRequest;
import com.example.blog.application.dto.LoginResponse;
import com.example.blog.application.dto.RegisterRequest;
import com.example.blog.application.dto.UserDto;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.UserRepository;
import com.example.blog.application.security.JwtUtil;
import com.example.blog.application.utils.exceptions.CustomExceptionResponse;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UsersService implements IUsersService {


    private final UserRepository userRepository;

    private final ModelMapper modelMapper;

    private final AuthenticationManager authenticationManager;

    private final JwtUtil jwtUtil;

    private final PasswordEncoder passwordEncoder;

    @Override
    public LoginResponse logInUser(LoginRequest request) {
        try {
            Authentication authentication = authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword())
            );

            UserDetails userDetails = (UserDetails) authentication.getPrincipal();

            // Generate JWT token
            String token = jwtUtil.generateToken(userDetails);

            HashMap<String, Object> claims = new HashMap<>();
            String refreshToken = jwtUtil.generateRefreshToken(claims, userDetails);
            Users users = userRepository.findByEmail(request.getEmail()).orElseThrow(
                    ()-> new CustomExceptionResponse("User Not found")
            );
            UserDto userDto = convertUserToDto(users);

            return LoginResponse.builder()
                    .token(token)
                    .refreshToken(refreshToken)
                    .user(userDto)
                    .build();
        } catch (AuthenticationException | CustomExceptionResponse e) {
            throw new CustomExceptionResponse(e.getMessage());
        }
    }

    @Override
    public Users createUser(RegisterRequest request) {
        return Optional.of(request).filter(user -> !userRepository.existsByEmail(request.getEmail())).map(req -> {
            Users users = new Users();
            users.setUsername(request.getUsername());
            users.setEmail(request.getEmail());
            users.setPassword(passwordEncoder.encode(request.getPassword()));  // Password encoding
//            users.setPicture(request.getPicture());
            return userRepository.save(users);

        }).orElseThrow(() -> new CustomExceptionResponse("Oops!" + request.getEmail() + " already exists") );
    }

//    @Override
//    public UserDto createUser(UserDto userDto) {
//        Users user = new Users();
//        user.setUsername(userDto.getUsername());
//        user.setEmail(userDto.getEmail());
//        user.setRole(userDto.getRole());
//
//        Users saved = userRepository.save(user);
//        return convertUserToDto(saved);
//    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::convertUserToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(this::convertUserToDto).orElse(null);
    }

    @Override
    public UserDto updateUser(Long id, UserDto userDto) {
        Optional<Users> optionalUser = userRepository.findById(id);
        if (optionalUser.isPresent()) {
            Users user = optionalUser.get();
            user.setUsername(userDto.getUsername());
            user.setEmail(userDto.getEmail());
            user.setRole(userDto.getRole());
            Users updated = userRepository.save(user);
            return convertUserToDto(updated);
        }
        return null;
    }

    @Override
    public boolean deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true;
        }
        return false;
    }

    // Helper method
    public UserDto convertUserToDto(Users users) {
       return modelMapper.map(users,UserDto.class);
    }
}
