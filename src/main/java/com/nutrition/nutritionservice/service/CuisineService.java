package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineDao;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 菜品 service
 *
 * @author heng.liu
 * @since 2020/12/23
 */
@Service
public class CuisineService {

    @Resource
    private CuisineDao cuisineDao;

    /**
     * 统计指定热量返回和目标的菜品数量。
     * 
     * @param minCalorie 热量最低值，包含。
     * @param maxCalorie 热量最高值，不包含。
     * @param goal 菜品推荐目标。
     */
    public int countByCalorieAndGoal(int minCalorie, int maxCalorie, int goal) {
        return cuisineDao.selectCountByCalorieAndGoal(minCalorie, maxCalorie, goal);
    }

}