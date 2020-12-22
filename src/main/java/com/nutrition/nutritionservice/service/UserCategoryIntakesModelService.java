package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserCategoryIntakesModelDao;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户膳食模型 Service。
 * 
 * @author heng.liu
 * @since 2020/12/22
 */
@Service
public class UserCategoryIntakesModelService {

    @Resource
    private UserCategoryIntakesModelDao userCategoryIntakesModelDao;

    public void saveUserModel(UserCategoryIntakesModelVo userModelVo) {
        userCategoryIntakesModelDao.insert(userModelVo);
    }

    public UserCategoryIntakesModelVo selectLastByUuid(String uuid) {
        return userCategoryIntakesModelDao.selectLastByUuid(uuid);
    }


}
