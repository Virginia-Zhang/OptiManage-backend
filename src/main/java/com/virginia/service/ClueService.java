package com.virginia.service;

import com.virginia.pojo.Clue;
import com.virginia.pojo.PageBean;
import com.virginia.query.GetCluesQuery;

import java.util.List;

/**
 * Service interfaces for marketing clue management
 * @author Virginia
 */
public interface ClueService {
    int addClue(Clue clue);
    Integer editClue(Clue clue);
    PageBean getAllClues(GetCluesQuery query);
    // Remove/Restore activities by ids
    Integer updateCluesByIds(List<Integer> ids, Integer isDeletedValue);
}
