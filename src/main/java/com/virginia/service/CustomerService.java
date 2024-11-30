package com.virginia.service;

import com.virginia.pojo.Customer;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCustomersQuery;

/**
 * Service methods for Customer entity
 * @author Virginia
 */
public interface CustomerService {
    Integer convertToCustomer(Customer customer);
    PageBean getAllCustomers(GetCustomersQuery query);
}
