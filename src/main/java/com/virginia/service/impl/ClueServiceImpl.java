package com.virginia.service.impl;

import com.virginia.mapper.ClueMapper;
import com.virginia.mapper.CustomerMapper;
import com.virginia.pojo.Clue;
import com.virginia.pojo.Customer;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCluesQuery;
import com.virginia.service.ClueService;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueMapper clueMapper;

    @Resource
    private CustomerMapper customerMapper;

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public int addClue(Clue clue) {
        // Get the current logged in user data from security context
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        clue.setCreateBy(loggedInUserInfo.getUser().getId());
        clue.setCreateTime(LocalDateTime.now());

        // Determine whether the current user is an administrator. If not, set owner id to the current logged in user id.
        if(!loggedInUserInfo.getRoleList().contains("admin")){
            clue.setOwnerId(loggedInUserInfo.getUser().getId());
        }
        int result = clueMapper.insertSelective(clue);

        // If the clue's state is 1, indicating it has been converted to a customer, then insert the customer data into the customer table simultaneously.
        if(clue.getState() == 1){
            convertClueToCustomer(clue, loggedInUserInfo);
        }
        return result;
    }

    // Convert clues with status of "Transferred to customer" to customer data
    public void convertClueToCustomer(Clue clue, MyUserDetails loggedInUserInfo){
        // Create customer data
        Customer customer = new Customer();
        customer.setClueId(clue.getId());
        customer.setProduct(clue.getIntentionProduct());
        customer.setDescription(clue.getDescription());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setRegion(clue.getRegion());
        customer.setCreateTime(LocalDateTime.now());
        customer.setCreateBy(loggedInUserInfo.getUser().getId());
        // Insert customer data
        customerMapper.insertSelective(customer);
    }

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer editClue(Clue clue) {
        clue.setEditTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        if (loggedInUserInfo != null) {
            clue.setEditBy(loggedInUserInfo.getUser().getId());
        }
        int result = clueMapper.updateByPrimaryKeySelective(clue);
        // If the clue's state is 1, indicating it has been converted to a customer, and this clue has not yet been added to the customer table, then insert the customer data into the customer table.
        int i = customerMapper.selectCustomerCountByClueId(clue.getId());
        if(clue.getState() == 1 && i == 0){
            assert loggedInUserInfo != null;
            convertClueToCustomer(clue, loggedInUserInfo);
        }
        return result;
    }

    /**
     *Query all clues by page and other conditions
     * @param query, which contains the current page number, page size, and other query conditions
     * @return PageBean, containing the total number of records and the current page records
     */
    @Override
    public PageBean getAllClues(GetCluesQuery query) {
        PageHelper.startPage(query.getPage(), query.getPageSize());
        List<Clue> clueList = clueMapper.selectAll(query);
        Page<Clue> pageInfo = (Page<Clue>) clueList;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public Integer updateCluesByIds(List<Integer> ids, Integer isDeletedValue) {
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        // The list of customer data IDs to be deleted or restored.
        List<Integer> customerIds;
        // Iterate over ids, check if the state of each clue is 1 - converted to customer. If so, add the id to customerIds; otherwise, do nothing.
        customerIds = ids.stream().filter(id -> {
            Clue clue = clueMapper.selectByPrimaryKey(id);
            return clue.getState() == 1;
        }).toList();
        // Batch delete or restore customer data corresponding to converted customer clues, passing in customerIds.
        int result1 = customerMapper.updateCustomersByClueIds(customerIds, isDeletedValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
        if(result1 != customerIds.size()){
            throw new RuntimeException("Deletion/restoration of customer data corresponding to converted customer leads failed! Please check and try again!");
        }
        // Batch delete or restore clue data
        int result2 = clueMapper.updateCluesByIds(ids, isDeletedValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
        // Compare result1 with result2. If they are equal, return result1; otherwise, throw an exception.
        if(result1 != result2){
            throw new RuntimeException("Deletion/restoration of lead data failed! Please try again later!");
        }
        return result1;
    }

    @Override
    public List<Clue> getAllCluesWithoutPagination(GetCluesQuery query) {
        return clueMapper.selectAll(query);
    }
}
