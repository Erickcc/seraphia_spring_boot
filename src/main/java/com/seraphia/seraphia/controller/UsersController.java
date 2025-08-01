package com.seraphia.seraphia.controller;

import com.seraphia.seraphia.dto.OrderRequest;
import com.seraphia.seraphia.model.Users;
import com.seraphia.seraphia.service.UsersService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/api/users")
@AllArgsConstructor
public class UsersController {

    private UsersService usersService;

    @GetMapping
    public List<Users> getAllUsers() {
        return usersService.getAllUsers();
    }

    @GetMapping(path = "{userId}")
    public Users getUserById(@PathVariable("userId") Long id) {
        return usersService.getUserById(id);
    }

    @PostMapping
    public Users addUser(@RequestBody Users user) {
        return usersService.addUser(user);
    }

    @DeleteMapping(path = "{userId}")
    public Users deleteUserById(@PathVariable("userId") Long id) {
        return usersService.deleteUserById(id);
    }

    @PutMapping(path = "{userId}")
    public Users updateUserById(@PathVariable("userId") Long id, @RequestBody Users userUpdated) {
        return usersService.updateUserById(id, userUpdated);
    }

    @PostMapping(path = "login")
    public boolean loginUser(@RequestBody Users user) {
        return usersService.validateUser(user);
    }

    @PostMapping(path = "{userId}/add-order")
    public Users addUserOrder(@PathVariable("userId") Long id, @RequestBody OrderRequest orderRequest) {
        return usersService.addOrder(id, orderRequest);
    }
}
