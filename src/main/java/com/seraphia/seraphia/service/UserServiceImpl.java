package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.UserRegisterDTO;
import com.seraphia.seraphia.model.Cart;
import com.seraphia.seraphia.model.User;
import com.seraphia.seraphia.repository.CartRepository;
import com.seraphia.seraphia.repository.UserRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import jakarta.transaction.Transactional;
import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public User registerUser(UserRegisterDTO userRegisterDTO) {
        Optional<User> existingUser = userRepository.findByEmail(userRegisterDTO.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User newUser = new User();
        newUser.setName(userRegisterDTO.getName());
        newUser.setPhone(userRegisterDTO.getPhone());
        newUser.setEmail(userRegisterDTO.getEmail());
        newUser.setPassword(userRegisterDTO.getPassword()); // En producción, encriptar la contraseña

        User savedUser = userRepository.save(newUser);

        Cart cart = new Cart(savedUser); // ✅ CORREGIDO: pasamos el objeto User, no el ID
        cartRepository.save(cart);

        return savedUser;
    }

    @Override
    public Optional<User> getUserByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    @Override
    public User updateUser(Long id, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(id);
        if (optionalUser.isEmpty()) {
            throw new IllegalArgumentException("El usuario con el id " + id + " no existe");
        }

        User existingUser = optionalUser.get();

        if (updatedUser.getName() != null) existingUser.setName(updatedUser.getName());
        if (updatedUser.getPhone() != null) existingUser.setPhone(updatedUser.getPhone());
        if (updatedUser.getEmail() != null) {
            Optional<User> userWithEmail = userRepository.findByEmail(updatedUser.getEmail());
            if (userWithEmail.isPresent() && !userWithEmail.get().getId().equals(id)) {
                throw new IllegalArgumentException("El correo electrónico ya está en uso por otro usuario");
            }
            existingUser.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) {
            existingUser.setPassword(updatedUser.getPassword());
        }

        return userRepository.save(existingUser);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("El usuario con el id " + id + " no existe");
        }
        cartRepository.findByUserId(id).ifPresent(cartRepository::delete);
        userRepository.deleteById(id);
        return true;
    }
}
