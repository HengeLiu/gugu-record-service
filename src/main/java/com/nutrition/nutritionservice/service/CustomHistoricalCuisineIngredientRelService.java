package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CustomHistoricalCuisineIngredientRelMapper;
import com.nutrition.nutritionservice.vo.CustomHistoricalCuisineIngredientRelVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2021/2/28
 */
@Service
public class CustomHistoricalCuisineIngredientRelService {

    @Resource
    private CustomHistoricalCuisineIngredientRelMapper customHistoricalCuisineIngredientRelMapper;

    @Transactional(rollbackFor = Exception.class)
    public void batchInsert(List<CustomHistoricalCuisineIngredientRelVo> relList) {
        customHistoricalCuisineIngredientRelMapper.batchInsert(relList);
    }

    public List<CustomHistoricalCuisineIngredientRelVo> selectByHistoricalCuisineId(long historicalCuisineId) {
        return customHistoricalCuisineIngredientRelMapper.selectByHistoricalCuisineId(historicalCuisineId);
    }

    @Transactional(rollbackFor = Exception.class)
    public void deleteByHistoricalCuisineId(long historicalCuisineId) {
        customHistoricalCuisineIngredientRelMapper.deleteByHistoricalCuisineId(historicalCuisineId);
    }

}
