package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.manager.CustomerManager;
import com.virginia.mapper.CustomerMapper;
import com.virginia.pojo.Customer;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCustomersQuery;
import com.virginia.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerManager customerManager;

    @Resource
    private CustomerMapper customerMapper;

    /**
     * Convert a lead into a customer, call convertToCustomer() in CustomerManager
     * @param customer Customer data
     * @return The number of entries inserted into the database. If it is 1, the insertion is successful, otherwise an exception will be thrown.
     */
    @Override
    public Integer convertToCustomer(Customer customer) {
        return customerManager.convertToCustomer(customer);
    }

    /**
     *Query all customers by page and other conditions
     * @param query, which contains the current page number, page size, and other query conditions
     * @return PageBean, containing the total number of records and the current page records
     */
    @Override
    public PageBean getAllCustomersByPage(GetCustomersQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Customer> customerList = customerMapper.selectAll(query);
        Page<Customer> pageInfo = (Page<Customer>) customerList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }

    /**
     * Get all customers without pagination
     * @return List of customers, without pagination
     */
    @Override
    public List<Customer> getAllCustomers(GetCustomersQuery query) {
        return customerMapper.selectAll(query);
    }
}
