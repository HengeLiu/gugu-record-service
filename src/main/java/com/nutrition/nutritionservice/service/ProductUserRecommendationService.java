package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.ProductUserRecommendationDao;
import com.nutrition.nutritionservice.vo.ProductUserRecommendationVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author heng.liu
 * @since 2021/1/15
 */
@Service
public class ProductUserRecommendationService {

    @Resource
    private ProductUserRecommendationDao productUserRecommendationDao;

    public int add(ProductUserRecommendationVo productUserRecommendationVo) {
        return productUserRecommendationDao.insert(productUserRecommendationVo);
    }

}
