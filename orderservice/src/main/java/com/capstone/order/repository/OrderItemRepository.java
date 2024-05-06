package com.capstone.order.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.order.entity.OrderItem;

public interface OrderItemRepository extends JpaRepository<OrderItem,Integer>{

}