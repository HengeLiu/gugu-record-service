package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import org.springframework.stereotype.Repository;

@Repository
public interface UserCategoryIntakesModelDao {

    int insert(UserCategoryIntakesModelVo record);

    int insertSelective(UserCategoryIntakesModelVo record);

    UserCategoryIntakesModelVo selectLastByUuid(String uuid);

}