package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface UserCategoryIntakesModelDao {

    int insert(UserCategoryIntakesModelVo record);

    int insertSelective(UserCategoryIntakesModelVo record);

    UserCategoryIntakesModelVo selectUsingModelByUuid(String uuid);

    int selectCountByCalorieAndGoal(@Param("calorie") int calorie, @Param("goal") int goal);

    void updateModelStatusByUuidAndCreateTime(String uuid, int modelStatus, LocalDateTime createTime);

}