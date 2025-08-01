package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.OrderRequest;
import com.seraphia.seraphia.model.Users;

import java.util.List;

public interface UsersService {
    List<Users> getAllUsers();
    Users getUserById(Long id);
    Users addUser(Users user);
    Users deleteUserById(Long id);
    Users updateUserById(Long id, Users user);
    Users addOrder(Long id, OrderRequest orderRequest);
    boolean validateUser(Users user);
}
