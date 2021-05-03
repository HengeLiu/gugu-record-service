package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CustomCuisineIngredientRelTmpDao;
import com.nutrition.nutritionservice.vo.CustomCuisineIngredientRelTmpVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 用户自定义菜品食材关系缓存Service
 *
 * @author heng.liu
 * @since 2021/5/3
 */
@Service
@Slf4j
public class CustomCuisineIngredientRelTmpService {

    @Resource
    private CustomCuisineIngredientRelTmpDao customCuisineIngredientRelTmpDao;

    @Transactional(rollbackFor = Exception.class)
    public int batchAdd(List<CustomCuisineIngredientRelTmpVo> cuisineIngredientRelList) {
        if (CollectionUtils.isEmpty(cuisineIngredientRelList)) {
            return 0;
        }
        return customCuisineIngredientRelTmpDao.batchInsert(cuisineIngredientRelList);
    }

}
