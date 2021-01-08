package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.IngredientVo;

import java.util.List;

public interface IngredientDao {
    int deleteByCode(Integer id);

    int insert(IngredientVo record);

    List<IngredientVo> selectByCategoryCode(int categoryCode);

    int insertSelective(IngredientVo record);

    IngredientVo selectByCode(String code);

    List<IngredientVo> selectByCodeList(List<Integer> codeList);

    int updateByCodeSelective(IngredientVo record);

    int updateByCode(IngredientVo record);
}