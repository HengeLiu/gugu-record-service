package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.LastAddedCuisineAo;
import com.nutrition.nutritionservice.controller.ao.UserInfoAo;
import com.nutrition.nutritionservice.converter.Model2UserModelConverter;
import com.nutrition.nutritionservice.converter.NutrientWeightVo2AoConverter;
import com.nutrition.nutritionservice.converter.UserInfoAo2VoConverter;
import com.nutrition.nutritionservice.enums.BooleanEnum;
import com.nutrition.nutritionservice.enums.UnitEnum;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.CuisineIngredientCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineIngredientRelService;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.IngredientNutrientRelService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.util.DateTimeUtil;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.util.UUIDUtils;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.IngredientNutrientRelVo;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 用户业务层。
 *
 * @author heng.liu
 * @since 2020/9/21
 */
@Biz
@Slf4j
public class UserBiz {

    @Resource
    private WechatHttpApiService wechatHttpApiService;

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private UserIngredientWeightSumDailyService userIngredientWeightSumDailyService;

    @Resource
    private UserNutrientWeightSumDailyService userNutrientWeightSumDailyService;

    @Resource
    private UserHistoricalCuisineService userHistoricalCuisineService;

    @Resource
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private CuisineIngredientCategoryWeightService cuisineIngredientCategoryWeightService;

    @Resource
    private CuisineIngredientRelService cuisineIngredientRelService;

    @Resource
    private IngredientNutrientRelService ingredientNutrientRelService;

    @Resource
    private UserNutrientWeightSumDailyBiz userNutrientWeightSumDailyBiz;

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

    @Resource
    private UserStatusInfoService userStatusInfoService;

    private UserInfoVo queryUserInfo(String uuid) {
        return userInfoService.selectByUuid(uuid);
    }

    public UserInfoVo loginWithWechat(String jsCode) {
        String wxOpenid = wechatHttpApiService.getUserOpenId(jsCode);
        if (StringUtils.isEmpty(wxOpenid)) {
            log.error("Cannot get wx.openid.");
            throw new NutritionServiceException("未查询到用户的微信账号");
        }
        UserAccountVo userAccount = userAccountService.queryByExternalIdAndType(wxOpenid, UserAccountTypeEnum.WEI_XIN);
        if (userAccount == null) {
            String uuid = UUIDUtils.createRandomUUID();
            userAccount = new UserAccountVo();
            userAccount.setUuid(uuid);
            userAccount.setType(UserAccountTypeEnum.WEI_XIN.getCode());
            userAccount.setExternalId(wxOpenid);
            userAccount.setStatus(UserAccountStatusTypeEnum.ENABLE.getCode());
            register(userAccount);
        }
        UserInfoVo userInfo = userInfoService.selectByUuid(userAccount.getUuid());
        if (userInfo == null) {
            userInfo = UserInfoVo.builder().uuid(userAccount.getUuid()).build();
        }
        return userInfo;
    }

    public void register(UserAccountVo userAccount) {
        if (userAccount.getPassword() == null) {
//            userAccount.setPassword("");
        }
        userAccountService.addUserAccount(userAccount);
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveUserInfo(UserInfoAo userInfoAo) {
        String uuid = userInfoAo.getUuid();
        UserIngredientCategoryModelVo userIngredientCategoryModelVo = Model2UserModelConverter.convert(
                modelIngredientCategoryModelBiz.calculateIngredientModel(userInfoAo), uuid,
                UserIngredientModelStatusEnum.ACTIVE);
        long activeModelId = userIngredientCategoryModelService.save(userIngredientCategoryModelVo);
        UserInfoVo userInfoVo = UserInfoAo2VoConverter.convert(userInfoAo, userIngredientCategoryModelVo.getCalorie(),
                activeModelId);
        userInfoService.add(userInfoVo);
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userIngredientWeightSumDailyVo == null) {
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }

        UserStatusInfoVo userStatusInfoVo = userStatusInfoService.queryBuUuid(uuid);
        if (userStatusInfoVo == null) {
            throw new NutritionServiceException("User status info can not null, uui " + uuid);
        }
        userStatusInfoVo.setShownInfoCollectWindow(BooleanEnum.TRUE.getCode());
        if (userInfoAo.isSkip()) {
            userStatusInfoVo.setCustomInfo(BooleanEnum.FALSE.getCode());
        } else {
            userStatusInfoVo.setCustomInfo(BooleanEnum.TRUE.getCode());
        }
        userStatusInfoService.updateByUuidSelective(userStatusInfoVo);

        Map<String, Object> resultParamMap = Maps.newHashMap();

        resultParamMap.put("userStatusInfo", userStatusInfoVo);
        resultParamMap.put("ingredientCategoryWeightList",
                ModelUtil.modelHistoryToWeightAo(
                        userIngredientCategoryModelService.queryById(userInfoVo.getActiveModelId()),
                        userIngredientWeightSumDailyVo));
        return resultParamMap;
    }

    public double calculateAndSaveUserCalorie(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        double calorie = energyCalorieCalculateService.calculateCalorieByUserInfo(userInfoVo);
        userInfoVo.setCalorie(calorie);
        userInfoService.add(userInfoVo);
        return calorie;
    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveCuisineHistory(String uuid, String cuisineCode) {

        Map<String, Object> resultParamMap = Maps.newHashMap();

        /* 更新餐品记录 */
        userHistoricalCuisineService.add(UserHistoricalCuisineVo.builder().uuid(uuid).cuisineCode(cuisineCode)
                .status(CuisineTasteEnum.UNEVALUATED.getCode()).build());
        resultParamMap.put("lastAddedCuisine", LastAddedCuisineAo.builder().cuisineCode(cuisineCode)
                .addedTime(DateTimeUtil.YMDHMS.format(LocalDateTime.now())).build());

        // 是否是当天第一条记录
        boolean dailyFirstRecode = false;

        /* 更新食材摄入历史记录 */
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightService
                .queryByCuisineCode(cuisineCode);
        if (cuisineIngredientCategoryWeightVo == null) {
            throw new NutritionServiceException(
                    "Cuisine ingredient category weight can not null, cuisine code " + cuisineCode);
        }
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userIngredientWeightSumDailyVo == null) {
            dailyFirstRecode = true;
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }
        userIngredientWeightSumDailyVo.addCuisineCategoryWeight(cuisineIngredientCategoryWeightVo);
        userIngredientWeightSumDailyService.insertOrUpdateByUuidAndDate(userIngredientWeightSumDailyVo);
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        if (userInfoVo == null) {
            throw new NutritionServiceException("User info can not null, uuid " + uuid);
        }
        resultParamMap.put("ingredientCategoryWeightList", ModelUtil.modelHistoryToWeightAo(
                userIngredientCategoryModelService.queryById(userInfoVo.getActiveModelId()),
                userIngredientWeightSumDailyVo));

        /* 更新营养素摄入历史记录 */
        List<CuisineIngredientRelVo> cuisineIngredientRelVoList = cuisineIngredientRelService
                .queryByCuisineCode(cuisineCode);
        if (CollectionUtils.isEmpty(cuisineIngredientRelVoList)) {
            throw new NutritionServiceException(
                    "Cuisine ingredient list can not be empty, cuisine code " + cuisineCode);
        }
        // 餐品食材重量
        Map<Integer, Integer> ingredientCodeWeightMap = cuisineIngredientRelVoList.stream().collect(
                Collectors.toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight));
        // 食材营养素含量
        Map<Integer, List<IngredientNutrientRelVo>> ingredientNutrientMap = ingredientNutrientRelService
                .queryByIngredientCodeList(Lists.newArrayList(ingredientCodeWeightMap.keySet())).stream()
                .collect(Collectors.groupingBy(IngredientNutrientRelVo::getIngredientCode));

        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = dailyFirstRecode ? Collections.emptyList()
                : userNutrientWeightSumDailyService.queryByUuidAndDate(uuid, LocalDate.now());
        // 用户营养素历史摄入量
        Map<Integer, Double> historicalNutrientCodeWeightMap = userNutrientWeightSumDailyVoList.stream()
                .collect(Collectors.toMap(UserNutrientWeightSumDailyVo::getNutrientCode,
                        UserNutrientWeightSumDailyVo::getWeight));
        for (Map.Entry<Integer, Integer> ingredientWeightEntry : ingredientCodeWeightMap.entrySet()) {
            Integer ingredientCode = ingredientWeightEntry.getKey();
            Integer ingredientWeight = ingredientWeightEntry.getValue();
            List<IngredientNutrientRelVo> ingredientNutrientRelList = ingredientNutrientMap.get(ingredientCode);
            if (CollectionUtils.isEmpty(ingredientNutrientRelList)) {
                log.error("Ingredient nutrient info is empty {}", ingredientCode);
                continue;
            }
            for (IngredientNutrientRelVo ingredientNutrientRelVo : ingredientNutrientRelList) {
                if ("none".equals(ingredientNutrientRelVo.getNutrientContent())
                        || "tr".equals(ingredientNutrientRelVo.getNutrientContent())
                        || UnitEnum.PERCENT.getName().equals(ingredientNutrientRelVo.getContentUnit())) {
                    continue;
                }
                double nutrientWeight = Double.parseDouble(ingredientNutrientRelVo.getNutrientContent());
                if (UnitEnum.MG.getName().equals(ingredientNutrientRelVo.getContentUnit())) {
                    nutrientWeight /= 1000;
                }
                historicalNutrientCodeWeightMap.put(ingredientNutrientRelVo.getNutrientCode(),
                        (nutrientWeight * ingredientWeight / 100) + historicalNutrientCodeWeightMap
                                        .getOrDefault(ingredientNutrientRelVo.getNutrientCode(), 0.0));
            }
        }
        List<UserNutrientWeightSumDailyVo> newUserNutrientWeightSumDailyVoList = historicalNutrientCodeWeightMap
                .entrySet().stream().map(entry -> UserNutrientWeightSumDailyVo.builder().uuid(uuid)
                        .nutrientCode(entry.getKey()).weight(entry.getValue()).date(LocalDate.now()).build())
                .collect(Collectors.toList());
        if (dailyFirstRecode) {
            userNutrientWeightSumDailyService.addAll(newUserNutrientWeightSumDailyVoList);
        } else {
            userNutrientWeightSumDailyService.updateAll(newUserNutrientWeightSumDailyVoList);
        }
        resultParamMap.put("userNutrientHistoricalIntakesDaily", NutrientWeightVo2AoConverter
                .convert(userIngredientWeightSumDailyVo.getCalorie(), newUserNutrientWeightSumDailyVoList));

        return resultParamMap;
    }

}
