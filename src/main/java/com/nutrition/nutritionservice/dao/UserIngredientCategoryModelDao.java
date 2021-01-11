package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface UserIngredientCategoryModelDao {

    long insert(UserIngredientCategoryModelVo record);

    UserIngredientCategoryModelVo selectActiveModelByUuid(String uuid);

    UserIngredientCategoryModelVo selectById(long id);

    int selectActiveCountByCalorieAndGoal(@Param("calorie") double calorie, @Param("goal") int goal);

    void updateModelStatusByUuid(String uuid, int modelStatus);

}