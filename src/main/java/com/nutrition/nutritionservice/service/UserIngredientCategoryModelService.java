package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserIngredientCategoryModelDao;
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
    private UserIngredientCategoryModelDao userIngredientCategoryModelDao;

    public long save(UserIngredientCategoryModelVo userModelVo) {
        userIngredientCategoryModelDao.updateModelStatusByUuid(userModelVo.getUuid(),
                UserIngredientModelStatusEnum.DEPRECATED.getCode());
        return userIngredientCategoryModelDao.insert(userModelVo);
    }

    @Nullable
    public UserIngredientCategoryModelVo queryById(long id) {
        return userIngredientCategoryModelDao.selectById(id);
    }

    public int countActiveByCalorieAndGoal(double calorie, int goal) {
        return userIngredientCategoryModelDao.selectActiveCountByCalorieAndGoal(calorie, goal);
    }


}
