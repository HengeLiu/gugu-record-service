package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserNutrientWeightSumDailyDao;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户营养素摄入重量每日汇总
 *
 * @author heng.liu
 * @since 2021/1/5
 */
@Service
public class UserNutrientWeightSumDailyService {

    @Resource
    private UserNutrientWeightSumDailyDao userNutrientWeightSumDailyDao;

    public List<UserNutrientWeightSumDailyVo> queryByUuidAndDate(String uuid, LocalDate date) {
        return userNutrientWeightSumDailyDao.selectByUuidAndDate(uuid, date);
    }

}