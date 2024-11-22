package com.virginia.mapper;

import com.virginia.annotation.DataFilterAnnotation;
import com.virginia.pojo.Clue;
import com.virginia.query.GetCluesQuery;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface ClueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);

    // Query marketing clues/leads with searching parameters, data filter SQL and pagination.
    // The parameters passed in are searching parameters with filterSQL used for data permission control.
    @DataFilterAnnotation(tableAlias = "tc", tableField = "owner_id")
    List<Clue> selectAll(GetCluesQuery query);
}