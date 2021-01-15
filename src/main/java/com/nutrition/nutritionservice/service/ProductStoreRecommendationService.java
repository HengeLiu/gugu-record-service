package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.ProductStoreRecommendationDao;
import com.nutrition.nutritionservice.vo.ProductStoreRecommendationVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2021/1/15
 */
@Service
public class ProductStoreRecommendationService {

    @Resource
    private ProductStoreRecommendationDao productStoreRecommendationDao;

    public int add(ProductStoreRecommendationVo productStoreRecommendationVo) {
        return productStoreRecommendationDao.insert(productStoreRecommendationVo);
    }

}
