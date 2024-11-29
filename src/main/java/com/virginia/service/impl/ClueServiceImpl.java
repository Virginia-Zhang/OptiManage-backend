package com.virginia.service.impl;

import com.virginia.mapper.ClueMapper;
import com.virginia.pojo.Clue;
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
        return clueMapper.insertSelective(clue);
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
        return clueMapper.updateByPrimaryKeySelective(clue);
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
        return clueMapper.updateCluesByIds(ids, isDeletedValue, LocalDateTime.now(), loggedInUserInfo.getUser().getId());
    }
}
