package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineAdditionalInfoDao;
import com.nutrition.nutritionservice.vo.CuisineAdditionalInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 餐品扩展信息
 *
 * @author heng.liu
 * @since 2021/1/11
 */
@Service
public class CuisineAdditionalInfoService {

    @Resource
    private CuisineAdditionalInfoDao cuisineAdditionalInfoDao;

    public CuisineAdditionalInfoVo queryByCuisineCode(String cuisineCode) {
        return cuisineAdditionalInfoDao.selectByCuisineCode(cuisineCode);
    }

}
