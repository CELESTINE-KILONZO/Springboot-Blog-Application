package com.example.blog.application.service.user;

import com.example.blog.application.dto.UserDto;
import com.example.blog.application.model.Users;
import com.example.blog.application.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UsersService implements IUsersService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDto createUser(UserDto userDto) {
        Users user = new Users();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setRole(userDto.getRole());

        Users saved = userRepository.save(user);
        return mapToDto(saved);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll().stream()
                .map(this::mapToDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto getUserById(Long id) {
        Optional<Users> user = userRepository.findById(id);
        return user.map(this::mapToDto).orElse(null);
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
            return mapToDto(updated);
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
    private UserDto mapToDto(Users user) {
        UserDto dto = new UserDto();
        dto.setUser_id(user.getUser_id());
        dto.setUsername(user.getUsername());
        dto.setEmail(user.getEmail());
        dto.setRole(user.getRole());
        return dto;
    }
}
