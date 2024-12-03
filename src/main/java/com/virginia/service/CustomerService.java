package com.virginia.service;

import com.virginia.pojo.Customer;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCustomersQuery;

import java.util.List;

/**
 * Service methods for Customer entity
 * @author Virginia
 */
public interface CustomerService {
    Integer convertToCustomer(Customer customer);
    PageBean getAllCustomersByPage(GetCustomersQuery query);
    // Query all customers without pagination
    List<Customer> getAllCustomers(GetCustomersQuery query);
}
