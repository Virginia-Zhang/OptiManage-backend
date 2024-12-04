package com.virginia.service;

import com.virginia.pojo.CustomerRemark;
import com.virginia.pojo.PageBean;

/**
 * Customer remark service methods
 * @author Virginia
 */
public interface CustomerRemarkService {
    Integer addCustomerRemark(CustomerRemark customerRemark);
    PageBean getAllCustomerRemarks(Integer customerId, Integer page, Integer pageSize, Integer isDeletedValue);
    Integer editCustomerRemark(CustomerRemark customerRemark);
    Integer deleteCustomerRemarkById(Integer id);
}
