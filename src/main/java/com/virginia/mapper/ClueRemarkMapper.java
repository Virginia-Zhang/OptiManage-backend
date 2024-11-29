package com.virginia.mapper;

import com.virginia.pojo.ClueRemark;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ClueRemarkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(ClueRemark record);

    int insertSelective(ClueRemark record);

    ClueRemark selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(ClueRemark record);

    int updateByPrimaryKey(ClueRemark record);

    // According to the clue ID, query all undeleted/deleted clue remarks under the current marketing clue.
    List<ClueRemark> selectAll(@Param("clueId") Integer clueId, @Param("isDeletedValue") Integer isDeletedValue);
}