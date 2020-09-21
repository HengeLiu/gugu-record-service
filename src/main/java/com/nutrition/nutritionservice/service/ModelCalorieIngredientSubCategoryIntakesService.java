package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.mapper.ModelCalorieIngredientSubCategoryIntakesMapper;
import com.nutrition.nutritionservice.vo.modeldata.ModelCalorieIngredientSubCategoryIntakesVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * 日需热量下的食材摄入量Service。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@Service
public class ModelCalorieIngredientSubCategoryIntakesService {

    @Resource
    private ModelCalorieIngredientSubCategoryIntakesMapper modelCalorieIngredientSubCategoryIntakesMapper;

    public List<ModelCalorieIngredientSubCategoryIntakesVo> queryByCalorie(double calorie) {
        return modelCalorieIngredientSubCategoryIntakesMapper.selectByCalorie(calorie);
    }

}
