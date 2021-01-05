package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.NutrientIntakesWeightAo;
import com.nutrition.nutritionservice.controller.ao.SupperIngredientCategoryWeightAo;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserHistoricalWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserLocationService;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserSettingService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.UserLocationVo;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.UserSettingVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 程序加载数据
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Biz
@Slf4j
public class ProgramLoadDataBiz {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserCategoryIntakesModelService userCategoryIntakesModelService;

    @Resource
    private UserHistoricalWeightSumDailyService userHistoricalWeightSumDailyService;

    @Resource
    private UserNutrientWeightSumDailyService userNutrientWeightSumDailyService;

    @Resource
    private UserSettingService userSettingService;

    @Resource
    private UserLocationService userLocationService;

    @Resource
    private UserHistoricalCuisineService userHistoricalCuisineService;

    public void loadUserInfo(String openid) {
        UserAccountVo userAccount = userAccountService.queryByExternalIdAndType(openid, UserAccountTypeEnum.WEI_XIN);
        if (userAccount != null) {

            String uuid = userAccount.getUuid();

            UserSettingVo userSettingVo = userSettingService.queryByUuid(uuid);
            UserLocationVo userLocationVo = null;
            if (userSettingVo.getLocationId() != null) {
                userLocationVo = userLocationService.queryByLocationId(userSettingVo.getLocationId());
            }

            UserHistoricalCuisineVo userHistoricalCuisineVo = userHistoricalCuisineService.queryLastAddedCuisine(uuid);

            // 获取用户食材分类模型目标值
            UserCategoryIntakesModelVo userIngredientModel = userCategoryIntakesModelService.queryLastByUuid(uuid);
            // 获取用户食材分类今日摄入历史
            UserHistoricalWeightSumDailyVo userHistoricalWeightSumDailyVo = userHistoricalWeightSumDailyService
                    .queryByUuidAndDate(uuid, LocalDate.now());
            /* 用户食材分类模型目标值及历史摄入量 */
            List<SupperIngredientCategoryWeightAo> supperIngredientCategoryWeightAoList = ModelUtil
                    .modelHistoryToWeightAo(userIngredientModel, userHistoricalWeightSumDailyVo);

            double historicalCalorie = userHistoricalWeightSumDailyVo.getCalorie();
            /* 获取用户营养素今日摄入历史 */
            List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = userNutrientWeightSumDailyService
                    .queryByUuidAndDate(uuid, LocalDate.now());
            Map<Integer, Double> nutrientCodeCalorieMap = Maps.newHashMap();
            nutrientCodeCalorieMap.put(NutrientEnum.CHO.getCode(), 4.0);
            nutrientCodeCalorieMap.put(NutrientEnum.Fat.getCode(), 9.0);
            nutrientCodeCalorieMap.put(NutrientEnum.Protein.getCode(), 4.0);
            List<NutrientIntakesWeightAo> nutrientWeightList = userNutrientWeightSumDailyVoList.stream()
                    .filter(nutrientWeight -> nutrientCodeCalorieMap.containsKey(nutrientWeight.getNutrientCode()))
                    .map(nutrientWeight -> {
                        NutrientEnum nutrientEnum = CodeEnums.valueOf(NutrientEnum.class,
                                nutrientWeight.getNutrientCode());
                        if (nutrientEnum == null) {
                            throw new IllegalArgumentException(
                                    "Nutrient not exist, code " + nutrientWeight.getNutrientCode());
                        }
                        return NutrientIntakesWeightAo.builder().nutrientCode(nutrientEnum.getCode())
                                .nutrientName(nutrientEnum.getNameZh()).weight(nutrientWeight.getWeight())
                                .calorie(
                                        nutrientWeight.getWeight() * nutrientCodeCalorieMap.get(nutrientEnum.getCode()))
                                .build();
                    }).collect(Collectors.toList());
            double calorieSum = nutrientWeightList.stream().mapToDouble(NutrientIntakesWeightAo::getCalorie).sum();
            // 热量计算误差处理
            nutrientWeightList.forEach(nutrientWeight -> {
                nutrientWeight.setPercent(nutrientWeight.getCalorie() / calorieSum);
                nutrientWeight.setCalorie(nutrientWeight.getPercent() * historicalCalorie);
            });

        }

    }
}
