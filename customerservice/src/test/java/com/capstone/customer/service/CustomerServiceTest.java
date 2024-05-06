package com.capstone.customer.service;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

import com.capstone.customer.entity.Customer;
import com.capstone.customer.entity.User;
import com.capstone.customer.exception.ResourceNotFoundException;
import com.capstone.customer.repository.CustomerRepository;

@SpringBootTest(properties="eureka.client.enabled=false")
public class CustomerServiceTest {
	
	@InjectMocks
	private CustomerServiceImpl customerService;
	
	@Mock
	private CustomerRepository customerRepository;
	
	@Test
	public void testViewCustomerDetailsById() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("sun");
		user.setPassword("sun1234");
		customer.setFirstName("sun");
		customer.setLastName("mathi");
		customer.setMobile("8874395428");
		customer.setEmail("sun12@gmail.com"); 
		customer.setAddress("tamilnadu");
		
		when(customerRepository.findById(1)).thenReturn(Optional.of(customer));
		Customer actualObj = customerService.getCustomerById(1);
		assertEquals("subbu", actualObj.getFirstName());
	}
	
	@Test
	public void testViewCustomerDetailsByIdWithException() {
		when(customerRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(ResourceNotFoundException.class, () -> customerService.getCustomerById(1));
	}
	
	@Test
	public void testViewAllCustomers() {
		User user = new User();
		Customer customer = new Customer();
		user.setUserId(1);
		user.setUsername("sun");
		user.setPassword("sun123");
		customer.setFirstName("Sun");
		customer.setLastName("Mathi");
		customer.setMobile("8688233328");
		customer.setEmail("sun12@gmail.com");
		customer.setAddress("tamilnadu");
		
		User user1 = new User();
		Customer customer1 = new Customer();
		user1.setUserId(2);
		user1.setUsername("sri");
		user1.setPassword("sri1234");
		customer1.setFirstName("sri");
		customer1.setLastName("mathi");
		customer1.setMobile("6957604909");
		customer1.setEmail("sri12@gmail.com");
		customer1.setAddress("chennai");
		
		User user2 = new User();
		Customer customer2 = new Customer();
		user2.setUserId(3);
		user2.setUsername("mohan");
		user2.setPassword("mohan123");
		customer2.setFirstName("mohan");
		customer2.setLastName("kumar");
		customer2.setMobile("8967450967");
		customer2.setEmail("mohan@gmail.com");
		customer2.setAddress("chennai");
		
		List<User> users = new ArrayList<>();
		List<Customer> customers = new ArrayList<>();
		users.add(user);
		users.add(user1);
		users.add(user2);
		customers.add(customer);
		customers.add(customer1);
		customers.add(customer2);
		 
		when(customerRepository.findAll()).thenReturn(customers);
		
		List<Customer> customerList = customerService.getAllCustomers();
		assertEquals(3, customerList.size());
	}
	@Test
	public void testSaveCustomer() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("Sun");
		user3.setPassword("sun1234");
		customer3.setFirstName("sun");
		customer3.setLastName("mitha");
		customer3.setMobile("9850956735");
		customer3.setEmail("sunmitha@example.com");
		customer3.setAddress("kochi");
		
		when(customerRepository.save(customer3)).thenReturn(customer3);
		
		Customer savedCustomer = customerService.saveCustomer(customer3);
		
		assertEquals("sun", savedCustomer.getFirstName());
		assertEquals("mitha", savedCustomer.getLastName());
		assertEquals("9850956735", savedCustomer.getMobile());
		assertEquals("sunmitha@example.com", savedCustomer.getEmail());
		assertEquals("kochi", savedCustomer.getAddress());
		
		verify(customerRepository, times(1)).save(customer3);
	}
	
	@Test
	public void testSaveCustomerWithException() {
		User user3 = new User();
		Customer customer3 = new Customer();
		user3.setUserId(4);
		user3.setUsername("Sun");
		user3.setPassword("sun1234");
		customer3.setFirstName("sun");
		customer3.setLastName("mitha");
		customer3.setMobile("9850956735");
		customer3.setEmail("sunmitha@example.com");
		customer3.setAddress("kochi");
		
		when(customerRepository.save(customer3)).thenThrow(new RuntimeException("Failed to save customer"));
		
		assertThrows(RuntimeException.class, () -> customerService.saveCustomer(customer3));
	}
	
	@Test
	public void testUpdateCustomer() {
	    // Create a customer object
	    Customer customer = new Customer();
	    customer.setUserId(1);
	    customer.setUsername("sun");
		customer.setPassword("sun123");
	    customer.setFirstName("sun");
	    customer.setLastName("mathi");
	    customer.setMobile("9785679456");
	    customer.setEmail("sun12@gmail.com");
	    customer.setAddress("tamilnadu");

	    // Assume customer exists in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

	    // Perform the update
	    Customer updatedCustomer = customerService.updateCustomerr(customer);

	    // Assertions
	    assertEquals("sun", updatedCustomer.getFirstName());
	    // Add more assertions as needed

	    // Verify that the save method was called once
	    verify(customerRepository, times(1)).save(customer);
	}

	@Test
	public void testUpdateCustomerWithException() {
	    // Create a customer object
		
		User user = new User();
	    Customer customer = new Customer();
	    user.setUserId(1);
	    user.setUsername("sun");
	    user.setPassword("sun123");
	    customer.setFirstName("sun");
	    customer.setLastName("mathi");
	    customer.setMobile("9785679456");
	    customer.setEmail("sun12@gmail.com");
	    customer.setAddress("tamilnadu");

	    // Assume customer does not exist in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the update and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> customerService.updateCustomerr(customer));
	}

	@Test
	public void testDeleteCustomerById() {
	    // Create a customer object
		User user = new User();
	    Customer customer = new Customer();
	    user.setUserId(1);
	    user.setUsername("sun");
	    user.setPassword("sun123");
	    customer.setFirstName("sun");
	    customer.setLastName("mathi");
	    customer.setMobile("9785679456");
	    customer.setEmail("sun12@gmail.com");
	    customer.setAddress("tamilnadu");

	    // Assume customer with ID 1 exists in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.of(customer));

	    // Perform the deletion
	    customerService.deleteCustomer(1);

	    // Verify that the delete method was called once with the correct customer
	    verify(customerRepository, times(1)).delete(customer);
	}

	@Test
	public void testDeleteCustomerByIdWithException() {
	    // Assume customer with ID 1 does not exist in the database
	    when(customerRepository.findById(1)).thenReturn(Optional.empty());

	    // Perform the deletion and expect an exception
	    assertThrows(ResourceNotFoundException.class, () -> customerService.deleteCustomer(1));
	}

	

}







