package com.capstone.customer.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.customer.entity.Customer;
import com.capstone.customer.service.CustomerService;

@RestController
@RequestMapping("/api/customer")
public class CustomerController {

    private final CustomerService customerService;

    @Autowired
    public CustomerController(CustomerService customerService) {
        this.customerService = customerService;
    }

    @PostMapping("/save")
    public ResponseEntity<Customer> saveCustomer(@RequestBody Customer customer) {
        customerService.saveCustomer(customer);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @GetMapping("/{id}")
	public ResponseEntity<Customer> fetchUserDetails(@PathVariable("id") int customerId) {
		Customer customer = customerService.getCustomerById(customerId);
			return new ResponseEntity<>(customer,HttpStatus.OK);
	}

    @GetMapping("/all")
    public ResponseEntity<List<Customer>> getAllCustomers() {
        List<Customer> customers = customerService.getAllCustomers();
        return new ResponseEntity<>(customers, HttpStatus.OK);
    }

	/*
	 * @PutMapping("/update") public ResponseEntity<User> editUser(@RequestBody User
	 * user) { userService.updateUser(user); ResponseEntity<User> responseEntity =
	 * new ResponseEntity<>(user,HttpStatus.OK); return responseEntity; }
	 * 
	 * @DeleteMapping("/{id}") public ResponseEntity<Void>
	 * deleteUser(@PathVariable("id") int userId) { userService.deleteUser(userId);
	 * ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
	 * return responseEntity; }
	 */

	/*
	 * @PostMapping("/admin") public Admin createAdmin(@RequestBody Admin admin) {
	 * return (Admin) userService.saveUser(admin); }
	 */

}