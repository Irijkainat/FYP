package com.FYP.HealthApp.service;

import com.FYP.HealthApp.Repositries.UserRepository;
import com.FYP.HealthApp.model.User;
import com.FYP.HealthApp.DTO.UserDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void register(UserDTO userDto) {
        User user = new User();
        user.setUsername(userDto.getUsername());
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setRole(userDto.getRole());
        userRepository.save(user);
    }

    public UserDTO getByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user == null) return null;
        return new UserDTO(user.getUsername(), user.getEmail(), user.getPassword(), user.getRole());
    }

    public boolean exists(String username) {
        return userRepository.existsByUsername(username);
    }
}
