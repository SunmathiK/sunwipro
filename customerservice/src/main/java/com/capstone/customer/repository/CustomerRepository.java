package com.capstone.customer.repository;
import org.springframework.data.jpa.repository.JpaRepository;

import com.capstone.customer.entity.Customer;

public interface CustomerRepository extends JpaRepository<Customer,Integer> {

}