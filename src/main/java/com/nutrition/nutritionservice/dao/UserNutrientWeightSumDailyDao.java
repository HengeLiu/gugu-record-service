package com.nutrition.nutritionservice.dao;

import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;

import java.time.LocalDate;
import java.util.List;

public interface UserNutrientWeightSumDailyDao {

    int insert(UserNutrientWeightSumDailyVo record);

    List<UserNutrientWeightSumDailyVo> selectByUuidAndDate(String uuid, LocalDate date);

    int updateWeightByUuidDateAndNutrientCode(UserNutrientWeightSumDailyVo record);

    int batchUpdate(List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList);

    int batchInsert(List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList);


}