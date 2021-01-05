package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserLocationDao;
import com.nutrition.nutritionservice.vo.UserLocationVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户地址
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Service
public class UserLocationService {

    @Resource
    private UserLocationDao userLocationDao;

    public UserLocationVo queryByLocationId(long id) {
        return userLocationDao.selectById(id);
    }

}
