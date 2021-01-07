package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserStatusInfoDao;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户状态信息
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Service
public class UserStatusInfoService {

    @Resource
    private UserStatusInfoDao userStatusInfoDao;

    @Nullable
    public UserStatusInfoVo queryBuUuid(String uuid) {
        return userStatusInfoDao.selectByUuid(uuid);
    }

    public int save(UserStatusInfoVo userStatusInfoVo) {
        return userStatusInfoDao.insertSelective(userStatusInfoVo);
    }

}
