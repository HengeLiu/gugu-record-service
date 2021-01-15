package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineNutrientWeightDao;
import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.List;

/**
 * 餐品营养素重量
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Service
public class CuisineNutrientWeightService {

    @Resource
    private CuisineNutrientWeightDao cuisineNutrientWeightDao;

    public List<CuisineNutrientWeightVo> queryByCuisineCode(String cuisineCode) {
        return cuisineNutrientWeightDao.selectByCuisineCode(cuisineCode);
    }

    public int add(CuisineNutrientWeightVo cuisineNutrientWeightVo) {
        return cuisineNutrientWeightDao.insert(cuisineNutrientWeightVo);
    }

    public void addAll(List<CuisineNutrientWeightVo> cuisineNutrientWeightVoList) {
        if (CollectionUtils.isEmpty(cuisineNutrientWeightVoList)) {
            return;
        }
        cuisineNutrientWeightDao.batchInsert(cuisineNutrientWeightVoList);
    }

    public int updateWeightByCuisineAndNutrientCode(String cuisineCode, int nutrientCode, double weight) {
        return cuisineNutrientWeightDao.updateWeightByCuisineAndNutrientCode(cuisineCode, nutrientCode, weight);
    }

}
