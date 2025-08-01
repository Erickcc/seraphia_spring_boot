package com.seraphia.seraphia.repository;

import com.seraphia.seraphia.model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;


public interface OrdersRepository extends JpaRepository<Orders, Long> {
}
