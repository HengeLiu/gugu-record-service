package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserInfoDao;
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
    private UserInfoDao userInfoDao;

    public UserInfoVo selectByUuid(String uuid) {
        return userInfoDao.selectByUuid(uuid);
    }

    public int add(UserInfoVo userInfo) {
        return userInfoDao.insert(userInfo);
    }

    public int updateSelective(UserInfoVo userInfo) {
        return userInfoDao.updateByUuidSelective(userInfo);
    }

}
