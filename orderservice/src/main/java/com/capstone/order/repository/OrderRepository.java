package com.capstone.order.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.order.entity.Order;

public interface OrderRepository extends JpaRepository<Order,Integer> {

}
