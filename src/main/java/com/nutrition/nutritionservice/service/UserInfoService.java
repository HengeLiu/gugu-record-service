package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.mapper.UserInfoMapper;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户信息Service。
 * 
 * @author heng.liu
 * @since 2020/9/28
 */
@Service
public class UserInfoService {

    @Resource
    private UserInfoMapper userInfoMapper;

    public UserInfoVo queryByUuid(String uuid) {
        return userInfoMapper.selectByUuid(uuid);
    }

    public int saveUserInfo(UserInfoVo userInfo) {
        return userInfoMapper.insertOrUpdate(userInfo);
    }

}
