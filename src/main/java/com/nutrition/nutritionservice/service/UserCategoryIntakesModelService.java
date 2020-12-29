package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserCategoryIntakesModelDao;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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

    @Transactional(rollbackFor = Exception.class)
    public void saveUserModel(UserCategoryIntakesModelVo userModelVo) {
        UserCategoryIntakesModelVo oldUserModelVo = userCategoryIntakesModelDao
                .selectUsingModelByUuid(userModelVo.getUuid());
        if (oldUserModelVo != null) {
            userCategoryIntakesModelDao.updateModelStatusByUuidAndCreateTime(userModelVo.getUuid(), 1,
                    userModelVo.getCreateTime());
        }
        userCategoryIntakesModelDao.insert(userModelVo);
    }

    public UserCategoryIntakesModelVo querySelectLastByUuid(String uuid) {
        return userCategoryIntakesModelDao.selectUsingModelByUuid(uuid);
    }

    public int countByCalorieAndGoal(int calorie, int goal) {
        return userCategoryIntakesModelDao.selectCountByCalorieAndGoal(calorie, goal);
    }


}
