package com.capstone.order.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.capstone.order.model.Customer;


@FeignClient(name = "customerservice", fallback = UserServiceFallback.class)
public interface UserServiceConsumer {

    @GetMapping("/customer/{id}")
    Customer getUserDetailsById(@PathVariable("id") int userId);
}