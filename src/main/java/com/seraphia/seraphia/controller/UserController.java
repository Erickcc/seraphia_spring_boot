package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.dto.UserLoginDTO;
import com.seraphia.seraphia.dto.UserRegisterDTO;
import com.seraphia.seraphia.dto.UserResponseDTO;
import com.seraphia.seraphia.dto.LoginResponseDTO;
import com.seraphia.seraphia.dto.CartDTO;
import com.seraphia.seraphia.dto.CartItemDTO;
import com.seraphia.seraphia.model.Cart;
import com.seraphia.seraphia.model.User;
import com.seraphia.seraphia.service.UserService;
import com.seraphia.seraphia.service.CartService;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;
import java.util.List;

@CrossOrigin("http://localhost:8080") // Permitir llamadas desde frontend
@RestController
@RequestMapping("/api/users")
@AllArgsConstructor
public class UserController {

    private final UserService userService;
    private final CartService cartService;

    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@RequestBody UserRegisterDTO dto) {
        try {
            User user = userService.registerUser(dto);
            return ResponseEntity.ok(new UserResponseDTO(user.getId(), user.getName(), user.getEmail()));
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginUser(@RequestBody UserLoginDTO dto) {
        Optional<User> optionalUser = userService.getUserByEmail(dto.getEmail());

        if (optionalUser.isEmpty()) {
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }

        User user = optionalUser.get();

        if (!user.getPassword().equals(dto.getPassword())) {
            return ResponseEntity.status(401).body("Contraseña incorrecta");
        }

        Cart cart = cartService.getOrCreateCartByUserId(user.getId());

        // Convertimos CartItem → CartItemDTO
        List<CartItemDTO> cartItemsDTO = cart.getItems().stream().map(item ->
                new CartItemDTO(
                        item.getId(),
                        item.getProduct().getId(),
                        item.getColor().getId(),
                        item.getSize().getId(),
                        item.getQuantity()
                )
        ).toList();

        CartDTO cartDTO = new CartDTO(cart.getId(), cartItemsDTO);

        LoginResponseDTO responseDTO = new LoginResponseDTO(
                user.getId(),
                user.getName(),
                user.getEmail(),
                cartDTO
        );

        return ResponseEntity.ok(responseDTO);
    }



    @GetMapping("/{id}")
    public ResponseEntity<?> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(value -> ResponseEntity.ok(new UserResponseDTO(value.getId(), value.getName(), value.getEmail())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable Long id) {
        try {
            boolean deleted = userService.deleteUser(id);
            return ResponseEntity.ok("Usuario eliminado con éxito");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }
}
