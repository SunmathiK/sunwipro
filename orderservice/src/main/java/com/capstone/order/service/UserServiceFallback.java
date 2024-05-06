package com.capstone.order.service;

import org.springframework.stereotype.Component;

import com.capstone.order.model.Customer;


@Component
public class UserServiceFallback implements UserServiceConsumer {

    @Override
    public Customer getUserDetailsById(int userId) {
        // Implement fallback logic here
        // For example, return a default User object or throw a custom exception
        return new Customer(); // Default user object
    }
}