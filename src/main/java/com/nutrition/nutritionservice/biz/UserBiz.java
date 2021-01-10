package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.UnitEnum;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.CuisineCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineIngredientRelService;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.IngredientNutrientRelService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.util.UUIDUtils;
import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;
import com.nutrition.nutritionservice.vo.IngredientNutrientRelVo;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
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
    private CuisineCategoryWeightService cuisineCategoryWeightService;

    @Resource
    private CuisineIngredientRelService cuisineIngredientRelService;

    @Resource
    private IngredientNutrientRelService ingredientNutrientRelService;

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

    public void saveUserInfo(UserInfoVo userInfoVo) {
        double calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
        userInfoVo.setCalorie(calorie);
        userInfoService.add(userInfoVo);
    }

    public int calculateAndSaveUserCalorie(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        int calorie = energyCalorieCalculateService.calculateByUserInfo(userInfoVo);
        userInfoVo.setCalorie((double) calorie);
        userInfoService.add(userInfoVo);
        return calorie;
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCuisineHistory(String uuid, String cuisineCode) {

        /* 更新餐品记录 */
        userHistoricalCuisineService.add(UserHistoricalCuisineVo.builder().uuid(uuid).cuisineCode(cuisineCode)
                .status(CuisineTasteEnum.UNEVALUATED.getCode()).build());

        // 是否是当天第一条记录
        boolean dailyFirstRecode = false;

        /* 更新食材摄入历史记录 */
        CuisineCategoryWeightVo cuisineCategoryWeightVo = cuisineCategoryWeightService.queryByCuisineCode(cuisineCode);
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userIngredientWeightSumDailyVo == null) {
            dailyFirstRecode = true;
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }
        userIngredientWeightSumDailyVo.addCuisineCategoryWeight(cuisineCategoryWeightVo);
        userIngredientWeightSumDailyService.insertOrUpdateByUuidAndDate(userIngredientWeightSumDailyVo);

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
    }

}
