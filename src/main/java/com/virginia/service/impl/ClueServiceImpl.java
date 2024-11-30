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
import io.micrometer.common.util.StringUtils;
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

        // 若clue的state为1，即已转客户，则要同时往客户表里插入客户数据
        if(clue.getState() == 1){
            convertClueToCustomer(clue, loggedInUserInfo);
        }
        return result;
    }

    // 将状态为已转客户的线索数据转换成客户数据
    public void convertClueToCustomer(Clue clue, MyUserDetails loggedInUserInfo){
        // 创建客户数据
        Customer customer = new Customer();
        customer.setClueId(clue.getId());
        customer.setProduct(clue.getIntentionProduct());
        customer.setDescription(clue.getDescription());
        customer.setNextContactTime(clue.getNextContactTime());
        customer.setRegion(clue.getRegion());
        customer.setCreateTime(LocalDateTime.now());
        customer.setCreateBy(loggedInUserInfo.getUser().getId());
        // 插入客户数据
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
        // 如果clue的state为1，即已转客户，且该线索还未被添加到客户表中，则往客户表中插入客户数据
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
        // 待删除/恢复的客户数据id集合
        List<Integer> customerIds;
        // 对传入的ids集合作遍历，检查每个线索的state是否为1-已转客户，是的话把id放入customerIds中，否则不做处理
        customerIds = ids.stream().filter(id -> {
            Clue clue = clueMapper.selectByPrimaryKey(id);
            return clue.getState() == 1;
        }).toList();
        // 批量删除/恢复已转客户线索对应的客户数据，传入customerIds
        int result1 = customerMapper.updateCustomersByClueIds(customerIds, isDeletedValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
        if(result1 != customerIds.size()){
            throw new RuntimeException("删除/恢复已转客户线索对应的客户数据失败！请检查后再试！");
        }
        // 批量删除/恢复线索数据
        int result2 = clueMapper.updateCluesByIds(ids, isDeletedValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
        // 比较result1是否等于result2，如果相等则返回result1，否则抛出异常
        if(result1 != result2){
            throw new RuntimeException("删除/恢复线索数据失败！请稍后再试！");
        }
        return result1;
    }
}
