package com.nutrition.nutritionservice.controller.ao;

import com.nutrition.nutritionservice.vo.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.CuisineVo;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 餐品设计
 * 
 * @author heng.liu
 * @since 2020/12/23
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuisineDesignerAo implements Serializable {

    private static final long serialVersionUID = 2127886022694767504L;

    /**
     * 菜品基础信息。
     */
    private CuisineVo cuisineVo;

    /**
     * 菜品和食材重量关系。
     */
    private List<CuisineIngredientRelVo> cuisineIngredientRelList;

}
