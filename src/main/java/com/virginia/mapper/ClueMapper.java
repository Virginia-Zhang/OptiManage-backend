package com.virginia.mapper;

import com.virginia.pojo.Clue;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface ClueMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Clue record);

    int insertSelective(Clue record);

    Clue selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Clue record);

    int updateByPrimaryKey(Clue record);
}