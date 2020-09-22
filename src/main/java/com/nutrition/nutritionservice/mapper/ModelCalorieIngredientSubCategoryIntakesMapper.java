package com.nutrition.nutritionservice.mapper;

import com.nutrition.nutritionservice.vo.modeldata.ModelCalorieIngredientSubCategoryIntakesVo;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * 日需热量下的食材摄入量Mapper。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@Mapper
public interface ModelCalorieIngredientSubCategoryIntakesMapper {

    @Select("select * from model_calorie_ingredient_sub_category_intakes where calorie = #{calorie}")
    List<ModelCalorieIngredientSubCategoryIntakesVo> selectByCalorie(double calorie);

    @Select("select max(calorie) from model_calorie_ingredient_sub_category_intakes where calorie <= #{calorie}")
    double selectMaxCalorieLte(double calorie);

}
