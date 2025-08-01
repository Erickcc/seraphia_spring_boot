package com.seraphia.seraphia.service;

import com.seraphia.seraphia.dto.OrderRequest;
import com.seraphia.seraphia.model.Orders;
import com.seraphia.seraphia.model.Users;
import com.seraphia.seraphia.repository.OrdersRepository;
import com.seraphia.seraphia.repository.UsersRepository;
import lombok.AllArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UsersServiceImpl implements UsersService{
    private final UsersRepository usersRepository;
    private final PasswordEncoder passwordEncoder;
    private final OrdersRepository ordersRepository;

    @Override
    public List<Users> getAllUsers() {
        return usersRepository.findAll();
    }

    @Override
    public Users getUserById(Long id) {
        return usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("El usuario con el ID " + id + " no existe")
        );
    }

    @Override
    public Users addUser(Users user) {
        String hashedPassword = this.passwordEncoder.encode(user.getPassword());
        user.setPassword(hashedPassword);
        return this.usersRepository.save(user);
    }

    @Override
    public Users deleteUserById(Long id) {
        Users tmp = null;
        if (!usersRepository.existsById(id)) return tmp;
        tmp = usersRepository.findById(id).get();
        usersRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Users updateUserById(Long id, Users userUpdated) {
        Optional<Users> optionalUsers = usersRepository.findById(id);
        Users originalUser = optionalUsers.get();
        if (optionalUsers.isEmpty()) throw new IllegalArgumentException("El usuario con el ID " + id + " no existe");

        if (userUpdated.getFirstName() != null) originalUser.setFirstName(userUpdated.getFirstName());

        if (userUpdated.getLastName() != null) originalUser.setLastName(userUpdated.getLastName());

        if (userUpdated.getEmail() != null) originalUser.setEmail(userUpdated.getEmail());

        if (userUpdated.getPhone() != null) originalUser.setPhone((userUpdated.getPhone()));

        if (userUpdated.getPassword() != null) originalUser.setPassword(passwordEncoder.encode(userUpdated.getPassword()));

        if (userUpdated.getRole() != null) originalUser.setRole(userUpdated.getRole());

        if (userUpdated.getDataRegister() != null) originalUser.setDataRegister(userUpdated.getDataRegister());

        if (userUpdated.getStreet() != null) originalUser.setStreet(userUpdated.getStreet());

        if (userUpdated.getNumInt() != null) originalUser.setNumInt(userUpdated.getNumInt());

        if (userUpdated.getNumExt() != null) originalUser.setNumExt(userUpdated.getNumExt());

        if (userUpdated.getSuburb() != null) originalUser.setSuburb(userUpdated.getSuburb());

        if (userUpdated.getZipCode() != null) originalUser.setZipCode(userUpdated.getZipCode());

        if (userUpdated.getCity() != null) originalUser.setCity(userUpdated.getCity());

        if (userUpdated.getState() != null) originalUser.setState(userUpdated.getState());

        return usersRepository.save(originalUser);
    }

    @Override
    public Users addOrder(Long id, OrderRequest orderRequest) {
        Users users = usersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La orden con la id " + id + " no existe"));
        Orders order = new Orders();

        if (orderRequest.getIdOrder() != null) {
            order.setIdOrder(orderRequest.getIdOrder());
        }
        order.setUser(users);
        ordersRepository.save(order);
        return usersRepository.save(users);
    }


    @Override
    public boolean validateUser(Users user) {
        Optional<Users> optionalUser = usersRepository.findByEmail(user.getEmail());
        if (optionalUser.isEmpty()) throw new IllegalArgumentException("El correo o contraseña son incorrectos");
        return passwordEncoder.matches(user.getPassword(), optionalUser.get().getPassword());
    }


}
