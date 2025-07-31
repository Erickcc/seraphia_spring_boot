package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.UserRegisterDTO;
import com.seraphia.seraphia.model.User;

import java.util.Optional;

public interface UserService {
    User registerUser(UserRegisterDTO dto);

    Optional<User> getUserByEmail(String email);

    Optional<User> getUserById(Long id);

    // Otros métodos que consideres necesarios
    User updateUser(Long id, User updatedUser);

    boolean deleteUser(Long id);
}
