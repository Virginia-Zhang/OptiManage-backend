package com.virginia.service.impl;

import com.virginia.manager.CustomerManager;
import com.virginia.pojo.Customer;
import com.virginia.service.CustomerService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;

@Service
public class CustomerServiceImpl implements CustomerService {
    @Resource
    private CustomerManager customerManager;

    /**
     * 将线索转换为客户，调用manager的convertCustomer方法
     * @param customer 客户数据
     * @return 插入数据库的条数，若为1，则表示插入成功，否则为0
     */
    @Override
    public Integer convertToCustomer(Customer customer) {
        return customerManager.convertToCustomer(customer);
    }
}
