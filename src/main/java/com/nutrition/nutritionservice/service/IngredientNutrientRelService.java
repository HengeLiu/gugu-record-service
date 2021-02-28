package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.IngredientNutrientRelDao;
import com.nutrition.nutritionservice.vo.IngredientNutrientRelVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 食材营养素
 *
 * @author heng.liu
 * @since 2021/1/10
 */
@Service
public class IngredientNutrientRelService {

    @Resource
    private IngredientNutrientRelDao ingredientNutrientRelDao;

    public List<IngredientNutrientRelVo> queryByIngredientCode(int ingredientCode) {
        return ingredientNutrientRelDao.selectByIngredientCode(ingredientCode);
    }

    public List<IngredientNutrientRelVo> queryByIngredientCodeList(List<Integer> ingredientCodeList) {
        if (CollectionUtils.isEmpty(ingredientCodeList)) {
            return Collections.emptyList();
        }
        return ingredientNutrientRelDao.selectByIngredientCodeList(ingredientCodeList);
    }
}
