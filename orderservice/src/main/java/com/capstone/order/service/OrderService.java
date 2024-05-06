package com.capstone.order.service;
import java.util.List;

import com.capstone.order.entity.Order;
import com.capstone.order.model.OrderResponse;
import com.capstone.order.payload.OrderItemPayload;

public interface OrderService {
	
	Order createOrder(int userId, List<OrderItemPayload> selectedMedicines);

	Order saveOrder(Order order);
	
	OrderResponse getOrderDetails(int orderId);
	
	List<OrderResponse> getAllOrders();
	
	Order updateOrder(Order order);

	void deleteOrder(int orderId);
	
//	List<Order> getAllOrdersByCustomer(int customerId);
}