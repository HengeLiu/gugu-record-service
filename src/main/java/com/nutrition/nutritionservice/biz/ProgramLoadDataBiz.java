package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.LastAddedCuisineAo;
import com.nutrition.nutritionservice.controller.ao.LocationAo;
import com.nutrition.nutritionservice.controller.ao.NutrientIntakesWeightAo;
import com.nutrition.nutritionservice.controller.ao.PreloadDataAo;
import com.nutrition.nutritionservice.controller.ao.SupperIngredientCategoryWeightAo;
import com.nutrition.nutritionservice.controller.ao.UserSettingsAo;
import com.nutrition.nutritionservice.converter.Model2UserModelConverter;
import com.nutrition.nutritionservice.converter.UserStatusInfoVo2AoConverter;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserHistoricalWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserLocationService;
import com.nutrition.nutritionservice.service.UserSettingService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.util.DateTimeUtil;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.UserLocationVo;
import com.nutrition.nutritionservice.vo.UserSettingVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private UserHistoricalWeightSumDailyService userHistoricalWeightSumDailyService;

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

    @Resource
    private ModelIngredientIntakesBiz modelIngredientIntakesBiz;

    @Resource
    private UserNutrientWeightSumDailyBiz userNutrientWeightSumDailyBiz;

    @Transactional(rollbackFor = Exception.class)
    public PreloadDataAo loadUserInfo(String openid) {
        PreloadDataAo.PreloadDataAoBuilder preloadDataAoBuilder = PreloadDataAo.builder();
        UserInfoVo userInfoVo;
        UserIngredientCategoryModelVo userIngredientCategoryModelVo;
        UserStatusInfoVo userStatusInfoVo;
        String uuid;
        UserSettingVo userSettingVo = null;
        UserAccountVo userAccount = userAccountService.queryByExternalIdAndType(openid, UserAccountTypeEnum.WEI_XIN);
        if (userAccount == null) {
            /* 为新用户创建默认数据 */

            uuid = UUID.randomUUID().toString().replace("-", "");
            userAccount = UserAccountVo.builder().uuid(uuid).externalId(openid)
                    .type(UserAccountTypeEnum.WEI_XIN.getCode()).status(UserAccountStatusTypeEnum.ENABLE.getCode())
                    .build();
            // 创建用户账号
            userAccountService.addUserAccount(userAccount);

            userInfoVo = configPropertiesService.getDefaultUserInfo();

            userIngredientCategoryModelVo = Model2UserModelConverter.convert(
                    modelIngredientIntakesBiz.calculateIntakesModel(userInfoVo), uuid,
                    UserIngredientModelStatusEnum.USING);
            // 创建用户模型
            long userModelId = userIngredientCategoryModelService.add(userIngredientCategoryModelVo);

            userInfoVo.setUuid(uuid);
            userInfoVo.setActiveModelId(userModelId);
            userInfoVo.setCalorie(userIngredientCategoryModelVo.getCalorie());
            // 创建用户信息
            userInfoService.add(userInfoVo);

            userStatusInfoVo = UserStatusInfoVo.builder().uuid(uuid).customInfo(0)
                    .shownInfoCollectWindow(0).shownProcessWindow(0).build();
            // 创建用户状态
            userStatusInfoService.save(userStatusInfoVo);

        } else {
            uuid = userAccount.getUuid();
            // 获取用户食材分类模型目标值
            userIngredientCategoryModelVo = userIngredientCategoryModelService.queryLastByUuid(uuid);
            if (userIngredientCategoryModelVo == null) {
                throw new NutritionServiceException("User model can not be null.");
            }
            userStatusInfoVo = userStatusInfoService.queryBuUuid(uuid);
            userSettingVo = userSettingService.queryByUuid(uuid);

        }

        preloadDataAoBuilder.uuid(uuid);
        preloadDataAoBuilder.modelCalorie(userIngredientCategoryModelVo.getCalorie());
        preloadDataAoBuilder.userStatusInfo(UserStatusInfoVo2AoConverter.INSTANCE.convert(userStatusInfoVo));

        /* 用户食材分类模型目标值及历史摄入量 */
        // 获取用户食材分类今日摄入历史
        UserHistoricalWeightSumDailyVo userHistoricalWeightSumDailyVo = userHistoricalWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userHistoricalWeightSumDailyVo == null) {
            userHistoricalWeightSumDailyVo = UserHistoricalWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }
        List<SupperIngredientCategoryWeightAo> supperIngredientCategoryWeightAoList = ModelUtil
                .modelHistoryToWeightAo(userIngredientCategoryModelVo, userHistoricalWeightSumDailyVo);
        preloadDataAoBuilder.ingredientCategoryWeightList(supperIngredientCategoryWeightAoList);

        double historicalCalorie = userHistoricalWeightSumDailyVo.getCalorie();
        preloadDataAoBuilder.historicalCalorieDaily(historicalCalorie);

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

        /* 获取用户营养素今日摄入历史 */
        Map<Integer, Double> nutrientCodeCalorieMap = Maps.newHashMap();
        nutrientCodeCalorieMap.put(NutrientEnum.CHO.getCode(), 4.0);
        nutrientCodeCalorieMap.put(NutrientEnum.Fat.getCode(), 9.0);
        nutrientCodeCalorieMap.put(NutrientEnum.Protein.getCode(), 4.0);
        List<NutrientIntakesWeightAo> nutrientWeightList = userNutrientWeightSumDailyBiz
                .queryUserNutrientWeightSumDaily(uuid, LocalDate.now(), historicalCalorie, nutrientCodeCalorieMap);
        preloadDataAoBuilder.userNutrientHistoricalIntakesDaily(nutrientWeightList);

        return preloadDataAoBuilder.build();
    }
}
