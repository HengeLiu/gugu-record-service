package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserHistoricalCuisineDao;
import com.nutrition.nutritionservice.vo.UserHistoricalCuisineVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户历史菜品记录Service。
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Service
public class UserHistoricalCuisineService {

    @Resource
    private UserHistoricalCuisineDao userHistoricalCuisineDao;

    public int countByCuisineCodeAndTaste(String cuisineCode, int taste) {
        return userHistoricalCuisineDao.countByCuisineCodeAndTaste(cuisineCode, taste);
    }

    public void add(UserHistoricalCuisineVo userHistoricalCuisineVo) {
        userHistoricalCuisineDao.insert(userHistoricalCuisineVo);
    }

}
