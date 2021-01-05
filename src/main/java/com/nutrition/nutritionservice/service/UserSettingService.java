package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserSettingDao;
import com.nutrition.nutritionservice.vo.UserSettingVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户设置
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Service
public class UserSettingService {

    @Resource
    private UserSettingDao userSettingDao;

    public UserSettingVo queryByUuid(String uuid) {
        return userSettingDao.selectByUuid(uuid);
    }

}
