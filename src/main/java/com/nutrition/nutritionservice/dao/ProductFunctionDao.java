package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.ProductFunctionVo;

import java.util.List;

public interface ProductFunctionDao {

    List<ProductFunctionVo> selectByStatus(Integer status);

    void updateVotesOneByCode(String code);
}