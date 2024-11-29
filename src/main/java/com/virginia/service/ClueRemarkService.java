package com.virginia.service;

import com.virginia.pojo.ClueRemark;
import com.virginia.pojo.PageBean;

/**
 * Service methods interface for ClueRemark entity
 * @author Virginia
 */
public interface ClueRemarkService {
    int addClueRemark(ClueRemark clueRemark);
    int editClueRemark(ClueRemark clueRemark);
    PageBean getAllClueRemarks(Integer clueId, Integer page, Integer pageSize, Integer isDeletedValue);
    int deleteClueRemarkById(Integer id);
}
