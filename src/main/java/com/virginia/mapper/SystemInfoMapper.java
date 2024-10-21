package com.virginia.mapper;

import com.virginia.pojo.SystemInfo;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface SystemInfoMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SystemInfo record);

    int insertSelective(SystemInfo record);

    SystemInfo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SystemInfo record);

    int updateByPrimaryKey(SystemInfo record);
}