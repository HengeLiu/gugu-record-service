package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;

import java.util.List;

public interface CuisineIngredientRelDao {
    int deleteByPrimaryKey(Integer id);

    int insert(CuisineIngredientRelVo record);

    int batchInsert(List<CuisineIngredientRelVo> relList);

    int insertSelective(CuisineIngredientRelVo record);

    CuisineIngredientRelVo selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(CuisineIngredientRelVo record);

    int updateByPrimaryKey(CuisineIngredientRelVo record);
}