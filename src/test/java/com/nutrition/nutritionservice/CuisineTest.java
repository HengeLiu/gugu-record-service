package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @author heng.liu
 * @since 2020/12/25
 */
@SpringBootTest
public class CuisineTest {

    @Resource
    private CuisineBiz cuisineBiz;

    @Test
    public void testRecommendedWeight() {
        Map<String, Integer> recommendedWeightMap = cuisineBiz.queryRecommendedCategoryWeightMap(1);
        System.out.println();
    }

}
