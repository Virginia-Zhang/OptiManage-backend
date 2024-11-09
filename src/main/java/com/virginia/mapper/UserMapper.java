package com.virginia.mapper;

import com.virginia.pojo.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.time.LocalDateTime;
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

    // Get all users with account_enabled = 1
    List<User> selectAll();

    // Remove/Restore users in batches
    int updateUsersByIds(@Param("ids") List<Integer> ids, @Param("accountEnabledValue") Integer accountEnabledValue, @Param("editTime") LocalDateTime editTime, @Param("editBy") Integer editBy);

    // Get all users with account_enabled = 0
    List<User> selectDeletedUsers();

    // Get all users with id and login_act only
    List<User> selectOwners();
}