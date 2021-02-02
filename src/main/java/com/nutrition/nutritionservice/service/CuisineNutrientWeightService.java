package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineNutrientWeightDao;
import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
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

    @Transactional(rollbackFor = Exception.class)
    public int add(CuisineNutrientWeightVo cuisineNutrientWeightVo) {
        return cuisineNutrientWeightDao.insert(cuisineNutrientWeightVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addAll(List<CuisineNutrientWeightVo> cuisineNutrientWeightVoList) {
        if (CollectionUtils.isEmpty(cuisineNutrientWeightVoList)) {
            return 0;
        }
        return cuisineNutrientWeightDao.batchInsert(cuisineNutrientWeightVoList);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateByCuisineCode(String cuisineCode, List<CuisineNutrientWeightVo> cuisineNutrientWeightVoList) {
        cuisineNutrientWeightDao.deleteByCuisineCode(cuisineCode);
        return this.addAll(cuisineNutrientWeightVoList);
    }

}
