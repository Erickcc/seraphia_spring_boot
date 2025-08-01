package com.seraphia.seraphia.service;

import com.seraphia.seraphia.model.Orders;
import com.seraphia.seraphia.model.Products;
import com.seraphia.seraphia.model.Users;
import com.seraphia.seraphia.repository.OrdersRepository;
import com.seraphia.seraphia.repository.ProductsRepository;
import com.seraphia.seraphia.repository.UsersRepository;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.aspectj.weaver.ast.Or;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
@AllArgsConstructor
public class OrdersServiceImpl implements OrdersService{
    private final OrdersRepository ordersRepository;
    private final ProductsRepository productsRepository;
    private final UsersRepository usersRepository;

    @Override
    public List<Orders> getAllOrders() {
        return ordersRepository.findAll();
    }

    @Override
    public Orders getOrderById(Long id) {
        return ordersRepository.findById(id).orElseThrow(
                () -> new IllegalArgumentException("La orden con el id " + id + " no existe")
        );
    }

    @Override
    @Transactional
    public Orders addOrder(Orders order) {
        // 1. Validar y cargar el usuario completo desde la base de datos.
        if (order.getUser() == null) {
            throw new IllegalArgumentException("La orden no tiene un usuario existente");
        }
        Users user = usersRepository.findById(order.getUser().getId())
                .orElseThrow(() ->
                        new IllegalArgumentException("El usuario con el id " + order.getUser().getId() + " no existe"));

        Set<Products> productsSummary = order.getProducts();
        if (productsSummary == null) {
            throw new IllegalArgumentException("La orden debe contener al menos un producto.");
        }
        Set<Long> productId = productsSummary.stream()
                .map(products -> products.getId()).collect(Collectors.toSet());

        Set<Products> productsList = new HashSet<>(productsRepository.findAllById(productId));

        BigDecimal netSale = productsList.stream()
                .map(products -> products.getPrice())
                .reduce(BigDecimal.ZERO, (precioAnterior, precioActual) -> precioAnterior.add(precioActual));

        Orders orders = new Orders();
        orders.setUser(user);
        orders.setProducts(productsList);
        orders.setNetSale(netSale);
        return ordersRepository.save(orders);
    }




    @Override
    public Orders deleteOrderById(Long id) {
        Orders tmp = null;
        if (!ordersRepository.existsById(id)) return tmp;
        tmp = ordersRepository.findById(id).get();
        ordersRepository.deleteById(id);
        return tmp;
    }

    @Override
    public Orders updateOrderById(Long id, Orders orderUpdated) {
        Optional<Orders> optionalOrders = ordersRepository.findById(id);
        Orders originalOrder = optionalOrders.get();
        if (optionalOrders.isEmpty()) throw new IllegalArgumentException("El producto con el id " + id + " no existe");
        if (originalOrder.getStatus() != null) originalOrder.setStatus(orderUpdated.getStatus());
        return ordersRepository.save(originalOrder);
    }
}
