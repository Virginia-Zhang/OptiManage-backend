package com.virginia.manager;

import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.ClueMapper;
import com.virginia.mapper.CustomerMapper;
import com.virginia.pojo.Clue;
import com.virginia.pojo.Customer;
import com.virginia.pojo.MyUserDetails;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * Customer service method，combined with clue mapper
 * @author Virginia
 */
@Component
public class CustomerManager {
    @Resource
    private CustomerMapper customerMapper;

    @Resource
    private ClueMapper clueMapper;

    // Convert lead/clue into customer
    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    public Integer convertToCustomer(Customer customer) {
        // 1. Verify whether the clue has been transferred to the customer. If the clue state corresponding to clue_id is 1, it means that the clue has been transferred to the customer and an exception will be thrown.
        Clue clue = clueMapper.selectByPrimaryKey(customer.getClueId());
        if (clue.getState() == 1) {
            throw new RuntimeException("This lead has been forwarded to customer, please do not repeat the operation!");
        }

        // 2. Insert customer data into t_customer table
        customer.setCreateTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        if(Objects.requireNonNull(loggedInUserInfo).getUser() != null){
            customer.setCreateBy(loggedInUserInfo.getUser().getId());
        }
        int result1 = customerMapper.insertSelective(customer);

        // 3. Change the lead/clue state to 1, that is, the lead has been transferred to customer
        clue.setState(1);
        clue.setEditTime(LocalDateTime.now());
        if(loggedInUserInfo.getUser() != null){
            clue.setEditBy(loggedInUserInfo.getUser().getId());
        }
        int result2 = clueMapper.updateByPrimaryKeySelective(clue);

        // If both 2 and 3 are successful, 1 will be returned, otherwise throw exception.
        if(result1 != 1 || result2 != 1){
            throw new RuntimeException("Failed to convert the lead to customer! Please try again later!");
        }
        return 1;
    }
}
