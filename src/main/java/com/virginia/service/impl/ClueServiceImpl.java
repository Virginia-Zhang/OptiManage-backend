package com.virginia.service.impl;

import com.virginia.mapper.ClueMapper;
import com.virginia.pojo.Clue;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCluesQuery;
import com.virginia.service.ClueService;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.virginia.annotation.LogAnnotation;

import java.util.List;

@Service
public class ClueServiceImpl implements ClueService {
    @Resource
    private ClueMapper clueMapper;

    @LogAnnotation
    @Override
    public int addClue(Clue clue) {
        return 0;
    }

    @LogAnnotation
    @Override
    public Integer editClue(Clue clue) {
        return 0;
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

    @LogAnnotation
    @Override
    public Integer updateCluesByIds(List<Integer> ids, Integer isDeletedValue) {
        return 0;
    }
}
