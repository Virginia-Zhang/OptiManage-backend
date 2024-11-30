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
     * 将线索转换为客户，调用manager的convertCustomer方法
     * @param customer 客户数据
     * @return 插入数据库的条数，若为1，则表示插入成功，否则为0
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
    public PageBean getAllCustomers(GetCustomersQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Customer> customerList = customerMapper.selectAll(query);
        Page<Customer> pageInfo = (Page<Customer>) customerList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }
}
