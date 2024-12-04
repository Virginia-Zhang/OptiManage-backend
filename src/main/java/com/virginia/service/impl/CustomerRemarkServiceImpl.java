package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.CustomerRemarkMapper;
import com.virginia.pojo.CustomerRemark;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.service.CustomerRemarkService;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

@Service
public class CustomerRemarkServiceImpl implements CustomerRemarkService {
    @Resource
    private CustomerRemarkMapper customerRemarkMapper;

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer addCustomerRemark(CustomerRemark customerRemark) {
        customerRemark.setCreateTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        if(Objects.requireNonNull(loggedInUserInfo).getUser() != null){
            customerRemark.setCreateBy(loggedInUserInfo.getUser().getId());
        }
        return customerRemarkMapper.insertSelective(customerRemark);
    }

    /**
     * Query all customer remarks with pagination
     * @param customerId Customer ID
     * @param page page number
     * @param pageSize page size
     * @param isDeletedValue whether the data is deleted or not
     * @return PageBean, including total number of data and data list of the page
     */
    @Override
    public PageBean getAllCustomerRemarks(Integer customerId, Integer page, Integer pageSize, Integer isDeletedValue) {
        PageHelper.startPage(page, pageSize);
        List<CustomerRemark> customerRemarks = customerRemarkMapper.selectAll(customerId, isDeletedValue);
        Page<CustomerRemark> pageInfo = (Page<CustomerRemark>) customerRemarks;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    public Integer editCustomerRemark(CustomerRemark customerRemark) {
        customerRemark.setEditTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        customerRemark.setEditBy(loggedInUserInfo.getUser().getId());
        return customerRemarkMapper.updateByPrimaryKeySelective(customerRemark);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    public Integer deleteCustomerRemarkById(Integer id) {
        CustomerRemark customerRemark = new CustomerRemark();
        customerRemark.setId(id);
        customerRemark.setIsDeleted(1);
        customerRemark.setEditTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        customerRemark.setEditBy(loggedInUserInfo.getUser().getId());
        return customerRemarkMapper.updateByPrimaryKeySelective(customerRemark);
    }


}
