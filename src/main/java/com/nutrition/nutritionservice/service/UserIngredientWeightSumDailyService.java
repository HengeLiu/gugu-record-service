package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserIngredientWeightSumDailyDao;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 用户历史摄入重量每日汇总。
 *
 * @author heng.liu
 * @since 2020/12/29
 */
@Service
public class UserIngredientWeightSumDailyService {

    @Resource
    private UserIngredientWeightSumDailyDao userIngredientWeightSumDailyDao;

    @Nullable
    public UserIngredientWeightSumDailyVo queryByUuidAndDate(String uuid, LocalDate date) {
        return userIngredientWeightSumDailyDao.selectByUuidAndDate(uuid, date);
    }

    public void insertOrUpdateByUuidAndDate(UserIngredientWeightSumDailyVo record) {
        if (userIngredientWeightSumDailyDao.selectByUuidAndDate(record.getUuid(), record.getDate()) == null) {
            userIngredientWeightSumDailyDao.insert(record);
        } else {
            userIngredientWeightSumDailyDao.updateByUuidAndDate(record);
        }
    }

}
