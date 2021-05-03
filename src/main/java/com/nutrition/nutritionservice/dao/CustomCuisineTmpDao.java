package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.CustomCuisineTmpVo;

public interface CustomCuisineTmpDao {

    int insert(CustomCuisineTmpVo record);

    CustomCuisineTmpVo selectByCuisineCode(String code);

}