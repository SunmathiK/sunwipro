package com.capstone.order.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.capstone.order.entity.Order;
import com.capstone.order.model.OrderResponse;
import com.capstone.order.payload.OrderItemPayload;
import com.capstone.order.payload.OrderPayload;
import com.capstone.order.service.OrderService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/save")
	public ResponseEntity<Order> placeOrder(@Valid @RequestBody OrderPayload orderPayload) {

		int userId = orderPayload.getUserId();
		List<OrderItemPayload> medicinesOrders = orderPayload.getOrderItems();

		Order order = orderService.createOrder(userId, medicinesOrders);
		Order newOrder = orderService.saveOrder(order);

		return new ResponseEntity<>(newOrder, HttpStatus.CREATED);
	}

	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> fetchOrderDetails(@PathVariable("id") int orderId) {
		OrderResponse order = orderService.getOrderDetails(orderId);
		return new ResponseEntity<>(order, HttpStatus.OK);
	}

	@GetMapping("/all")
	public List<OrderResponse> fetchAllOrders() {
		List<OrderResponse> orders = orderService.getAllOrders();
		return orders;
	}

	@PutMapping("/update")
	public  ResponseEntity<Order> editOrder(@Valid @RequestBody Order order) {
		orderService.updateOrder(order);
		ResponseEntity<Order> responseEntity = new ResponseEntity<>(order,HttpStatus.OK);
		return responseEntity;
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteOrder(@PathVariable("id") int orderId) {
		orderService.deleteOrder(orderId);
		ResponseEntity<Void> responseEntity = new ResponseEntity<>(HttpStatus.OK);
		return responseEntity;	
	}
}