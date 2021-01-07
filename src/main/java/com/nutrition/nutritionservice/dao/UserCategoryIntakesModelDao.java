package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserCategoryIntakesModelDao {

    long insert(UserIngredientCategoryModelVo record);

    UserIngredientCategoryModelVo selectUsingModelByUuid(String uuid);

    int selectCountByCalorieAndGoal(@Param("calorie") double calorie, @Param("goal") int goal);

    void updateModelStatusByUuidAndCreateTime(String uuid, int modelStatus, LocalDateTime createTime);

}