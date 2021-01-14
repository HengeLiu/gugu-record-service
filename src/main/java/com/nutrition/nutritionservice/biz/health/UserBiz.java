package com.nutrition.nutritionservice.biz.health;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.CuisinePreviewAo;
import com.nutrition.nutritionservice.controller.health.ao.UserInfoAo;
import com.nutrition.nutritionservice.converter.Model2UserModelConverter;
import com.nutrition.nutritionservice.converter.NutrientWeightVo2AoConverter;
import com.nutrition.nutritionservice.converter.UserInfoAo2VoConverter;
import com.nutrition.nutritionservice.enums.BooleanEnum;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.CuisineIngredientCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineIngredientRelService;
import com.nutrition.nutritionservice.service.CuisineNutrientWeightService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.service.StoreService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.util.DateTimeUtil;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.StoreVo;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
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
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

    @Resource
    private UserStatusInfoService userStatusInfoService;

    @Resource
    private CuisineNutrientWeightService cuisineNutrientWeightService;

    @Resource
    private CuisineService cuisineService;

    @Resource
    private StoreService storeService;

    @Resource
    private IngredientService ingredientService;

    public void register(UserAccountVo userAccount) {

    }

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveUserInfo(UserInfoAo userInfoAo) {
        String uuid = userInfoAo.getUuid();

        /* 为用户建立食材类型模型 */

        UserIngredientCategoryModelVo userIngredientCategoryModelVo = Model2UserModelConverter.convert(
                modelIngredientCategoryModelBiz.calculateIngredientModel(userInfoAo), uuid,
                UserIngredientModelStatusEnum.ACTIVE);
        // 保存用户食材类型模型
        long activeModelId = userIngredientCategoryModelService.save(userIngredientCategoryModelVo);
        UserInfoVo userInfoVo = UserInfoAo2VoConverter.convert(userInfoAo,
                userIngredientCategoryModelVo.getTargetCalorie(),
                activeModelId);

        // 保存用户信息
        userInfoService.add(userInfoVo);

        // 维护用户状态
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

        resultParamMap.put("targetCalorie", userIngredientCategoryModelVo.getTargetCalorie());
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

        /* 更新营养素摄入历史记录 */

        List<CuisineIngredientRelVo> cuisineIngredientRelVoList = cuisineIngredientRelService
                .queryByCuisineCode(cuisineCode);
        if (CollectionUtils.isEmpty(cuisineIngredientRelVoList)) {
            throw new NutritionServiceException(
                    "Cuisine ingredient list can not be empty, cuisine code " + cuisineCode);
        }
        // 餐品营养素含量
        Map<Integer, Double> cuisineNutrientCodeWeightMap = cuisineNutrientWeightService.queryByCuisineCode(cuisineCode)
                .stream().collect(
                        Collectors.toMap(CuisineNutrientWeightVo::getNutrientCode, CuisineNutrientWeightVo::getWeight));
        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = dailyFirstRecode ? Collections.emptyList()
                : userNutrientWeightSumDailyService.queryByUuidAndDate(uuid, LocalDate.now());
        Map<Integer, Double> userNutrientCodeWeightSumDailyMap = userNutrientWeightSumDailyVoList.stream()
                .collect(Collectors.toMap(UserNutrientWeightSumDailyVo::getNutrientCode,
                        UserNutrientWeightSumDailyVo::getWeight));
        // 用户营养素历史摄入量
        Map<Integer, Double> userHistoricalNutrientCodeWeightMap = Maps.newHashMap();
        for (NutrientEnum nutrientEnum : NutrientEnum.values()) {
            double historicalNutrientCodeWeight = cuisineNutrientCodeWeightMap.getOrDefault(nutrientEnum.getCode(), 0.0)
                    + userNutrientCodeWeightSumDailyMap.getOrDefault(nutrientEnum.getCode(), 0.0);
            if (historicalNutrientCodeWeight != 0) {
                userHistoricalNutrientCodeWeightMap.put(nutrientEnum.getCode(), historicalNutrientCodeWeight);
            }
        }
        List<UserNutrientWeightSumDailyVo> newUserNutrientWeightSumDailyVoList = userHistoricalNutrientCodeWeightMap
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

        resultParamMap.put("historicalCuisineList", queryTodayCuisineHistory(uuid));
        return resultParamMap;
    }

    public List<CuisinePreviewAo> queryTodayCuisineHistory(String uuid) {
        List<UserHistoricalCuisineVo> userHistoricalCuisineVoList = userHistoricalCuisineService
                .queryByUuidAndDate(uuid, LocalDate.now());
        List<String> cuisineCodeList = userHistoricalCuisineVoList.stream().map(UserHistoricalCuisineVo::getCuisineCode)
                .collect(Collectors.toList());
        List<CuisineVo> cuisineVoList = cuisineService.queryByCuisineCodeList(cuisineCodeList);
        List<StoreVo> storeVoList = storeService
                .queryByCodeList(cuisineVoList.stream().map(CuisineVo::getStoreCode).collect(Collectors.toList()));
        Map<String, StoreVo> storeCodeMap = storeVoList.stream()
                .collect(Collectors.toMap(StoreVo::getCode, Function.identity()));
        Map<String, CuisineVo> cuisineCodeMap = cuisineVoList.stream()
                .collect(Collectors.toMap(CuisineVo::getCode, Function.identity()));
        List<CuisineIngredientRelVo> cuisineIngredientRelVoList = cuisineIngredientRelService
                .queryByCuisineCodeList(cuisineCodeList);
        Map<Integer, String> ingredientCodeNameMap = ingredientService
                .queryByCodeList(cuisineIngredientRelVoList.stream().map(CuisineIngredientRelVo::getIngredientCode)
                        .distinct().collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(IngredientVo::getCode, IngredientVo::getName));
        Map<String, List<CuisineIngredientRelVo>> cuisineIngredientMap = cuisineIngredientRelVoList.stream()
                .collect(Collectors.groupingBy(CuisineIngredientRelVo::getCuisineCode));
        List<CuisinePreviewAo> cuisinePreviewAoList = Lists.newArrayList();
        for (UserHistoricalCuisineVo historicalCuisineVo : userHistoricalCuisineVoList) {
            CuisineVo cuisineVo = cuisineCodeMap.get(historicalCuisineVo.getCuisineCode());
            StoreVo storeVo = storeCodeMap.get(cuisineVo.getStoreCode());
            List<CuisineIngredientRelVo> ingredientRelVoList = cuisineIngredientMap.getOrDefault(cuisineVo.getCode(),
                    Collections.emptyList());
            List<String> mainIngredientNameList = ingredientRelVoList.stream()
                    .sorted(Comparator.comparingInt(CuisineIngredientRelVo::getWeight).reversed())
                    .filter(cuisineIngredientRelVo -> ingredientCodeNameMap
                            .containsKey(cuisineIngredientRelVo.getIngredientCode()))
                    .map(cuisineIngredientRelVo -> ingredientCodeNameMap
                            .get(cuisineIngredientRelVo.getIngredientCode()))
                    .collect(Collectors.toList());
            cuisinePreviewAoList.add(CuisinePreviewAo.builder().code(cuisineVo.getCode()).name(cuisineVo.getName())
                    .imageUrl(cuisineVo.getImageUrl())
                    .lastAddedDateTime(DateTimeUtil.YMDHMS.format(historicalCuisineVo.getCreateTime()))
                    .storeCode(storeVo.getCode()).storeName(storeVo.getName())
                    .mainIngredientList(mainIngredientNameList).build());

        }
        return cuisinePreviewAoList;
    }

    public UserInfoAo queryUserInfo(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        return UserInfoAo2VoConverter.revert(userInfoVo);
    }

}
