package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.LastAddedCuisineAo;
import com.nutrition.nutritionservice.controller.ao.LocationAo;
import com.nutrition.nutritionservice.controller.ao.NutrientIntakesWeightAo;
import com.nutrition.nutritionservice.controller.ao.PreloadDataAo;
import com.nutrition.nutritionservice.controller.ao.SupperIngredientCategoryWeightAo;
import com.nutrition.nutritionservice.controller.ao.UserSettingsAo;
import com.nutrition.nutritionservice.converter.UserStatusInfoVo2AoConverter;
import com.nutrition.nutritionservice.enums.CodeEnums;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserHistoricalWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserLocationService;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserSettingService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.util.DateTimeUtil;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.UserLocationVo;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.UserSettingVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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

    @Resource
    private UserStatusInfoService userStatusInfoService;

    @Resource
    private ConfigPropertiesService configPropertiesService;

    public PreloadDataAo loadUserInfo(String openid) {
        PreloadDataAo.PreloadDataAoBuilder preloadDataAoBuilder = PreloadDataAo.builder();
        UserAccountVo userAccount = userAccountService.queryByExternalIdAndType(openid, UserAccountTypeEnum.WEI_XIN);
        if (userAccount == null) {
            /* 新用户创建用户信息 */
            String uuid = UUID.randomUUID().toString().replace("-", "");
            userAccount = UserAccountVo.builder().uuid(uuid).externalId(openid)
                    .type(UserAccountTypeEnum.WEI_XIN.getCode()).status(UserAccountStatusTypeEnum.ENABLE.getCode())
                    .build();
            userAccountService.addUserAccount(userAccount);

            UserInfoVo defaultUserInfo = configPropertiesService.getDefaultUserInfo();
            defaultUserInfo.setUuid(uuid);
            userInfoService.save(defaultUserInfo);

            // userCategoryIntakesModelService.countByCalorieAndGoal()

            UserStatusInfoVo userStatusInfoVo = UserStatusInfoVo.builder().uuid(uuid).customInfo(0)
                    .shownInfoCollectWindow(0).shownProcessWindow(0).build();
            userStatusInfoService.save(userStatusInfoVo);

        }

        String uuid = userAccount.getUuid();
        preloadDataAoBuilder.uuid(uuid);

        // 获取用户食材分类模型目标值
        UserCategoryIntakesModelVo userIngredientModel = userCategoryIntakesModelService.queryLastByUuid(uuid);
        if (userIngredientModel == null) {
            throw new NutritionServiceException("User model can not be null.");
        }
        preloadDataAoBuilder.modelCalorie(userIngredientModel.getCalorie());

        // 获取用户食材分类今日摄入历史
        UserHistoricalWeightSumDailyVo userHistoricalWeightSumDailyVo = userHistoricalWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userHistoricalWeightSumDailyVo == null) {
            userHistoricalWeightSumDailyVo = UserHistoricalWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }
        preloadDataAoBuilder.historicalCalorieDaily(userHistoricalWeightSumDailyVo.getCalorie());

        UserSettingVo userSettingVo = userSettingService.queryByUuid(uuid);
        if (userSettingVo != null) {
            LocationAo userLocationAo = null;
            if (userSettingVo.getLocationId() != null) {
                UserLocationVo userLocationVo = userLocationService.queryByLocationId(userSettingVo.getLocationId());
                userLocationAo = LocationAo.builder().title(userLocationVo.getTitle())
                        .latitude(userLocationVo.getLatitude()).longitude(userLocationVo.getLongitude()).build();

            }
            preloadDataAoBuilder
                    .userSettings(UserSettingsAo.builder().defaultTakeawayPlatformCode(userSettingVo.getTakeawayCode())
                            .defaultOrderLocation(userLocationAo).build());
        }

        preloadDataAoBuilder.systemDefaultOrderLocation(configPropertiesService.getSystemDefaultLocation());

        UserHistoricalCuisineVo lastUserHistoricalCuisineVo = userHistoricalCuisineService.queryLastAddedCuisine(uuid);
        if (lastUserHistoricalCuisineVo != null) {
            preloadDataAoBuilder.lastAddedCuisine(
                    LastAddedCuisineAo.builder().cuisineCode(lastUserHistoricalCuisineVo.getCuisineCode())
                            .addedTime(DateTimeUtil.MDHMS.format(lastUserHistoricalCuisineVo.getCreateTime())).build());
        }

        UserStatusInfoVo userStatusInfoVo = userStatusInfoService.queryBuUuid(uuid);
        preloadDataAoBuilder.userStatusInfo(UserStatusInfoVo2AoConverter.INSTANCE.convert(userStatusInfoVo));

        /* 用户食材分类模型目标值及历史摄入量 */
        List<SupperIngredientCategoryWeightAo> supperIngredientCategoryWeightAoList = ModelUtil
                .modelHistoryToWeightAo(userIngredientModel, userHistoricalWeightSumDailyVo);
        preloadDataAoBuilder.ingredientCategoryWeightList(supperIngredientCategoryWeightAoList);

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
                    NutrientEnum nutrientEnum = CodeEnums.valueOf(NutrientEnum.class, nutrientWeight.getNutrientCode());
                    if (nutrientEnum == null) {
                        throw new IllegalArgumentException(
                                "Nutrient not exist, code " + nutrientWeight.getNutrientCode());
                    }
                    return NutrientIntakesWeightAo.builder().nutrientCode(nutrientEnum.getCode())
                            .nutrientName(nutrientEnum.getNameZh()).weight(nutrientWeight.getWeight())
                            .calorie(nutrientWeight.getWeight() * nutrientCodeCalorieMap.get(nutrientEnum.getCode()))
                            .build();
                }).collect(Collectors.toList());
        double calorieSum = nutrientWeightList.stream().mapToDouble(NutrientIntakesWeightAo::getCalorie).sum();
        // 热量计算误差处理
        nutrientWeightList.forEach(nutrientWeight -> {
            nutrientWeight.setPercent(nutrientWeight.getCalorie() / calorieSum);
            nutrientWeight.setCalorie(nutrientWeight.getPercent() * historicalCalorie);
        });
        preloadDataAoBuilder.userNutrientHistoricalIntakesDaily(nutrientWeightList);

        return preloadDataAoBuilder.build();
    }
}
