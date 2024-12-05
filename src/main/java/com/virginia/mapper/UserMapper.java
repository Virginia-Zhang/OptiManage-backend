package com.virginia.mapper;

import com.virginia.pojo.User;
import com.virginia.query.GetUsersQuery;
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

    // Query users with searching parameters and pagination.
    List<User> selectAll(GetUsersQuery query);

    // Remove/Restore users in batches
    int updateUsersByIds(@Param("ids") List<Integer> ids, @Param("accountEnabledValue") Integer accountEnabledValue, @Param("editTime") LocalDateTime editTime, @Param("editBy") Integer editBy);

    // Get all users with id, login_act and region only
    List<User> selectOwners();
}