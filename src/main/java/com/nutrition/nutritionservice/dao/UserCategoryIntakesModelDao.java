package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;

public interface UserCategoryIntakesModelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(UserCategoryIntakesModelVo record);

    int insertSelective(UserCategoryIntakesModelVo record);

    UserCategoryIntakesModelVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(UserCategoryIntakesModelVo record);

    int updateByPrimaryKey(UserCategoryIntakesModelVo record);
}