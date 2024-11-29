package com.virginia.service.impl;

import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.ClueRemarkMapper;
import com.virginia.pojo.ClueRemark;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.PageBean;
import com.virginia.service.ClueRemarkService;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class ClueRemarkServiceImpl implements ClueRemarkService {
    @Resource
    private ClueRemarkMapper clueRemarkMapper;

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public int addClueRemark(ClueRemark clueRemark) {
        clueRemark.setCreateTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        clueRemark.setCreateBy(loggedInUserInfo.getUser().getId());
        return clueRemarkMapper.insertSelective(clueRemark);
    }

    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public int editClueRemark(ClueRemark clueRemark) {
        clueRemark.setEditTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        clueRemark.setEditBy(loggedInUserInfo.getUser().getId());
        return clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
    }

    /**
     * 根据clueId获取该线索的所有未删除的/已删除的备注，分页
     * @param clueId id of the marketing clue
     * @param page page number
     * @param pageSize page size
     * @param isDeletedValue whether the data is deleted or not
     * @return PageBean, including total number of data and data list of the page
     */
    @Override
    public PageBean getAllClueRemarks(Integer clueId, Integer page, Integer pageSize, Integer isDeletedValue) {
        PageHelper.startPage(page, pageSize);
        List<ClueRemark> clueRemarks = clueRemarkMapper.selectAll(clueId, isDeletedValue);
        Page<ClueRemark> pageInfo = (Page<ClueRemark>) clueRemarks;
        return new PageBean(pageInfo.getTotal(), pageInfo.getResult());
    }

    /**
     * Delete clue remark by id, update is_deleted field to 1
     * @param id clue id
     * @return Number of rows affected
     */
    @Transactional(rollbackFor = Exception.class)
    @LogAnnotation
    @Override
    public int deleteClueRemarkById(Integer id) {
        ClueRemark clueRemark = new ClueRemark();
        clueRemark.setId(id);
        clueRemark.setIsDeleted(1);
        clueRemark.setEditTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        assert loggedInUserInfo != null;
        clueRemark.setEditBy(loggedInUserInfo.getUser().getId());
        return clueRemarkMapper.updateByPrimaryKeySelective(clueRemark);
    }
}
