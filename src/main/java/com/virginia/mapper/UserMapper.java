package com.virginia.mapper;

import com.virginia.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Date;
import java.util.List;

@Mapper
public interface UserMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(User record);

    int insertSelective(User record);

    User selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(User record);

    int updateByPrimaryKey(User record);

    User selectByLoginAct(String username);

    List<User> selectAll();

    // Delete users in batches
    int deleteUsersByIds(@Param("ids") List<Integer> ids, @Param("editTime") Date editTime, @Param("editBy") Integer editBy);
}