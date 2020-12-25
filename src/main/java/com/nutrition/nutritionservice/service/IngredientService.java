package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.IngredientDao;
import com.nutrition.nutritionservice.vo.IngredientVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2020/12/23
 */
@Service
public class IngredientService {

    @Resource
    private IngredientDao ingredientDao;

    public List<IngredientVo> queryByCategoryCode(int categoryCode) {
        return ingredientDao.selectByCategoryCode(categoryCode);
    }

}