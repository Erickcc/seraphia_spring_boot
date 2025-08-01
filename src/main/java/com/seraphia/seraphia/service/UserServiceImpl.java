package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.UserRegisterDTO;
import com.seraphia.seraphia.model.Cart;
import com.seraphia.seraphia.model.User;
import com.seraphia.seraphia.repository.CartRepository;
import com.seraphia.seraphia.repository.UserRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@AllArgsConstructor
@Transactional
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final CartRepository cartRepository;

    @Override
    public User registerUser(UserRegisterDTO dto) {
        Optional<User> existingUser = userRepository.findByEmail(dto.getEmail());
        if (existingUser.isPresent()) {
            throw new IllegalArgumentException("El email ya está registrado");
        }

        User newUser = new User();
        newUser.setName(dto.getName());
        newUser.setPhone(dto.getPhone());
        newUser.setEmail(dto.getEmail());
        newUser.setPassword(dto.getPassword()); // En producción: encriptar

        User savedUser = userRepository.save(newUser);

        // Crear carrito asociado
        Cart cart = new Cart(savedUser);
        savedUser.setCart(cart); // para la persistencia bidireccional
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
        User user = userRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("El usuario con el id " + id + " no existe"));

        if (updatedUser.getName() != null) user.setName(updatedUser.getName());
        if (updatedUser.getPhone() != null) user.setPhone(updatedUser.getPhone());
        if (updatedUser.getEmail() != null) {
            Optional<User> existing = userRepository.findByEmail(updatedUser.getEmail());
            if (existing.isPresent() && !existing.get().getId().equals(id)) {
                throw new IllegalArgumentException("El correo electrónico ya está en uso por otro usuario");
            }
            user.setEmail(updatedUser.getEmail());
        }
        if (updatedUser.getPassword() != null) user.setPassword(updatedUser.getPassword());

        return userRepository.save(user);
    }

    @Override
    public boolean deleteUser(Long id) {
        if (!userRepository.existsById(id)) {
            throw new IllegalArgumentException("El usuario con el id " + id + " no existe");
        }

        // Elimina primero el carrito si existe
        cartRepository.findByUserId(id).ifPresent(cartRepository::delete);

        userRepository.deleteById(id);
        return true;
    }

    @Override
    public boolean existsById(Long id) {
        return userRepository.existsById(id);
    }
}
