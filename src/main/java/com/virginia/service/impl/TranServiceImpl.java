package com.virginia.service.impl;

import com.virginia.annotation.LogAnnotation;
import com.virginia.mapper.TranMapper;
import com.virginia.pojo.MyUserDetails;
import com.virginia.pojo.Tran;
import com.virginia.service.TranService;
import com.virginia.utils.UserUtils;
import jakarta.annotation.Resource;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Objects;

@Service
public class TranServiceImpl implements TranService {
    @Resource
    private TranMapper tranMapper;

    @LogAnnotation
    @Transactional(rollbackFor = Exception.class)
    @Override
    public Integer addTran(Tran tran) {
        tran.setCreateTime(LocalDateTime.now());
        MyUserDetails loggedInUserInfo = UserUtils.getLoggedInUserInfo();
        if(Objects.requireNonNull(loggedInUserInfo).getUser() != null){
            tran.setCreateBy(loggedInUserInfo.getUser().getId());
        }
        return tranMapper.insertSelective(tran);
    }
}
