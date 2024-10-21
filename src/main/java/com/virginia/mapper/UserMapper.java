package com.virginia.mapper;

import com.virginia.pojo.User;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    User selectByLoginAct(String username);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);
}