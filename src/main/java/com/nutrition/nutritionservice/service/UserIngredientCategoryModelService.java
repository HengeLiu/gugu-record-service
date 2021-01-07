package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserCategoryIntakesModelDao;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import org.checkerframework.checker.nullness.qual.Nullable;
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
@Transactional(rollbackFor = Exception.class)
public class UserIngredientCategoryModelService {

    @Resource
    private UserCategoryIntakesModelDao userCategoryIntakesModelDao;

    public long add(UserIngredientCategoryModelVo userModelVo) {
        return userCategoryIntakesModelDao.insert(userModelVo);
    }

    public void updateModelStatusByUuidAndCreateTime(UserIngredientCategoryModelVo userModelVo) {
        userCategoryIntakesModelDao.updateModelStatusByUuidAndCreateTime(userModelVo.getUuid(),
                UserIngredientModelStatusEnum.USING.getCode(), userModelVo.getCreateTime());
    }

    @Nullable
    public UserIngredientCategoryModelVo queryLastByUuid(String uuid) {
        return userCategoryIntakesModelDao.selectUsingModelByUuid(uuid);
    }

    public int countByCalorieAndGoal(double calorie, int goal) {
        return userCategoryIntakesModelDao.selectCountByCalorieAndGoal(calorie, goal);
    }


}
