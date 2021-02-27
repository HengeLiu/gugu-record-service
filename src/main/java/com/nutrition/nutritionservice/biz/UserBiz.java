package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.CuisineIngredientAo;
import com.nutrition.nutritionservice.controller.ao.CuisinePreviewAo;
import com.nutrition.nutritionservice.controller.ao.CustomIntakesHistoryAo;
import com.nutrition.nutritionservice.controller.ao.UserInfoAo;
import com.nutrition.nutritionservice.converter.Model2UserModelConverter;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.CustomUserInfoStatusEnum;
import com.nutrition.nutritionservice.enums.database.IngredientCategoryEnum;
import com.nutrition.nutritionservice.enums.database.NutrientEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.CuisineIngredientCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineIngredientRelService;
import com.nutrition.nutritionservice.service.CuisineNutrientWeightService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.service.StoreService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserHistoricalOrderService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserNutrientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.util.CuisineUtil;
import com.nutrition.nutritionservice.util.DateTimeUtil;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.CuisineIngredientRelVo;
import com.nutrition.nutritionservice.vo.CuisineNutrientWeightVo;
import com.nutrition.nutritionservice.vo.CuisineVo;
import com.nutrition.nutritionservice.vo.HistoricalCuisineRecordVo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.StoreVo;
import com.nutrition.nutritionservice.vo.UserHistoricalOrderVo;
import com.nutrition.nutritionservice.vo.UserNutrientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.modeldata.CategoryModel;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientCategoryModelVo;
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
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.UUID;
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
    private UserIngredientWeightSumDailyService userIngredientWeightSumDailyService;

    @Resource
    private UserNutrientWeightSumDailyService userNutrientWeightSumDailyService;

    @Resource
    private UserHistoricalCuisineService userHistoricalCuisineService;

    @Resource
    private UserHistoricalOrderService userHistoricalOrderService;

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

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Resource
    private IngredientBiz ingredientBiz;

    @Resource
    private CuisineBiz cuisineBiz;

    @Transactional(rollbackFor = Exception.class)
    public Map<String, Object> saveUserInfo(UserInfoAo userInfoAo) {
        String uuid = userInfoAo.getUuid();

        /* 为用户建立食材类型模型 */
        double targetCalorie = saveUserInfoAndModel(userInfoAo, uuid);

        userStatusInfoService.updateCustomInfoStatusByUuid(CustomUserInfoStatusEnum.ENTERED.getCode(), uuid);

        Map<String, Object> resultParamMap = Maps.newHashMap();
        resultParamMap.put("targetCalorie", targetCalorie);
        return resultParamMap;
    }

    /**
     * 根据指定的openid，为用户创建账号、默认用户信息、默认健康模型、默认用户状态，并且保存。
     * 
     * @param openid 目前为微信openid。
     */
    @Transactional(rollbackFor = Exception.class)
    public UserAccountVo creatAccountUserInfoAndModel(String openid) {
        /* 为新用户创建默认数据 */

        String uuid = UUID.randomUUID().toString().replace("-", "");
        UserAccountVo userAccount = UserAccountVo.builder().uuid(uuid).externalId(openid)
                .type(UserAccountTypeEnum.WEI_XIN.getCode()).status(UserAccountStatusTypeEnum.ENABLE.getCode()).build();
        // 创建用户账号
        userAccountService.addUserAccount(userAccount);

        // 保存用户信息和模型
        UserInfoVo userInfoVo = configPropertiesService.getDefaultUserInfo();
        saveUserInfoAndModel(userInfoVo, uuid);

        UserStatusInfoVo userStatusInfoVo = UserStatusInfoVo.builder().uuid(uuid).customInfo(0)
                .shownInfoCollectWindow(0).showProcessWindow(0).build();
        // 创建用户状态
        userStatusInfoService.add(userStatusInfoVo);
        return userAccount;
    }

    /**
     * 保存用户信息并更新模型
     * 
     * @param modelParamVo 模型参数
     * @param uuid uuid
     * @return 用户日需目标热量
     */
    @Transactional(rollbackFor = Exception.class)
    public double saveUserInfoAndModel(ModelParamVo modelParamVo, String uuid) {
        ModelIngredientCategoryModelVo modelIngredientCategoryModelVo = modelIngredientCategoryModelBiz
                .calculateIngredientModel(modelParamVo);
        UserIngredientCategoryModelVo userIngredientCategoryModelVo = Model2UserModelConverter
                .convert(modelIngredientCategoryModelVo);
        userIngredientCategoryModelVo.setUuid(uuid);
        userIngredientCategoryModelVo.setModelStatus(UserIngredientModelStatusEnum.ACTIVE.getCode());
        long modelId = userIngredientCategoryModelService.save(userIngredientCategoryModelVo);
        userIngredientCategoryModelVo.setId(modelId);

        UserInfoVo userInfoVo = UserInfoVo.builder().uuid(uuid).activeModelId(userIngredientCategoryModelVo.getId())
                .age(modelParamVo.getAge()).targetCalorie(userIngredientCategoryModelVo.getCalorie())
                .standardCalorie(modelIngredientCategoryModelVo.getStandardCalorie()).gender(modelParamVo.getGender())
                .goal(modelParamVo.getGoal()).height(modelParamVo.getHeight()).weight(modelParamVo.getWeight())
                .profeChar(modelParamVo.getProfeChar()).sportsHabits(modelParamVo.getSportsHabits()).build();
        // 保存用户信息
        userInfoService.addOrUpdate(userInfoVo);
        return userInfoVo.getTargetCalorie();
    }

    @Transactional(rollbackFor = Exception.class)
    public void removeCuisineHistory(String uuid, long userHistoricalCuisineId) {
        /* 更新餐品记录 */
        UserHistoricalCuisineVo removedCuisineHistory = userHistoricalCuisineService.remove(userHistoricalCuisineId);
        if (removedCuisineHistory == null) {
            log.error("Cuisine history null, user " + uuid + ", historical cuisine id " + userHistoricalCuisineId);
            return;
        }
        LocalDate historyLocalDate = removedCuisineHistory.getCreateTime().toLocalDate();

        /* 更新食材摄入历史记录 */
        String cuisineCode = removedCuisineHistory.getCuisineCode();
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightService
                .queryByCuisineCode(cuisineCode);
        if (cuisineIngredientCategoryWeightVo == null) {
            throw new NutritionServiceException(
                    "Cuisine ingredient category weight can not null, cuisine code " + cuisineCode);
        }
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, historyLocalDate);
        if (userIngredientWeightSumDailyVo == null) {
            throw new NutritionServiceException("Empty ingredient weight sum daily at "
                    + DateTimeUtil.YMD.format(historyLocalDate) + "for user " + uuid);
        }
        userIngredientWeightSumDailyVo.minusCuisineCategoryWeight(cuisineIngredientCategoryWeightVo);
        userIngredientWeightSumDailyService.update(userIngredientWeightSumDailyVo);

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

        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = userNutrientWeightSumDailyService
                .queryByUuidAndDate(uuid, historyLocalDate);
        if (CollectionUtils.isEmpty(userNutrientWeightSumDailyVoList)) {
            throw new NutritionServiceException("Empty nutrient weight sum daily at "
                    + DateTimeUtil.YMD.format(historyLocalDate) + "for user " + uuid);
        }

        Map<Integer, Double> userHistoricalNutrientCodeWeightMap = userNutrientWeightSumDailyVoList.stream()
                .collect(Collectors.toMap(UserNutrientWeightSumDailyVo::getNutrientCode,
                        UserNutrientWeightSumDailyVo::getWeight));
        // 用户营养素历史摄入量
        Map<Integer, Double> newUserHistoricalNutrientCodeWeightMap = Maps.newHashMap();
        for (NutrientEnum nutrientEnum : NutrientEnum.values()) {
            double historicalNutrientCodeWeight = userHistoricalNutrientCodeWeightMap
                    .getOrDefault(nutrientEnum.getCode(), 0.0)
                    - cuisineNutrientCodeWeightMap.getOrDefault(nutrientEnum.getCode(), 0.0);
            if (historicalNutrientCodeWeight > 0) {
                newUserHistoricalNutrientCodeWeightMap.put(nutrientEnum.getCode(), historicalNutrientCodeWeight);
            }
        }
        List<UserNutrientWeightSumDailyVo> newUserNutrientWeightSumDailyVoList = newUserHistoricalNutrientCodeWeightMap
                .entrySet().stream().map(entry -> UserNutrientWeightSumDailyVo.builder().uuid(uuid)
                        .nutrientCode(entry.getKey()).weight(entry.getValue()).date(historyLocalDate).build())
                .collect(Collectors.toList());
        userNutrientWeightSumDailyService.replaceAll(uuid, historyLocalDate, newUserNutrientWeightSumDailyVoList);
    }

    public void saveCustomCuisineHistory(CustomIntakesHistoryAo customIntakesHistoryAo, LocalDate targetDate) {
        /* 更新餐品记录 */
        userHistoricalCuisineService.add(UserHistoricalCuisineVo.builder().uuid(customIntakesHistoryAo.getUuid())
                .cuisineCode(customIntakesHistoryAo.getCuisineCode()).status(CuisineTasteEnum.UNEVALUATED.getCode())
                .build());

        List<CuisineIngredientAo> ingredientWeightList = customIntakesHistoryAo.getIngredientList();
        if (CollectionUtils.isEmpty(ingredientWeightList)) {
            log.error("Ingredient list is empty, cuisine code {}, uuid {}", customIntakesHistoryAo.getCuisineCode(),
                    customIntakesHistoryAo.getUuid());
        }

        Map<Integer, Integer> integerWeightMap = ingredientWeightList.stream()
                .collect(Collectors.toMap(CuisineIngredientAo::getCode, CuisineIngredientAo::getWeight));
        /* 本次摄入热量 */
        double calorie = ingredientBiz.calculateCalorieByIngredientWeight(integerWeightMap);
        /* 餐品食材分类重量 */
        Map<IngredientCategoryEnum, Integer> ingredientCategoryEnumIntegerMap = cuisineIngredientCategoryWeightService
                .calculateIngredientCategoryWight(integerWeightMap);
        /* 餐品营养素含量 */
        Map<Integer, Double> nutrientWeightMap = cuisineBiz.calculateNutrientWeight(integerWeightMap);
        if (CollectionUtils.isEmpty(nutrientWeightMap)) {
            log.error("Cuisine nutrient weight is empty, cuisine code " + customIntakesHistoryAo.getCuisineCode());
        }
        this.saveIntakes(customIntakesHistoryAo.getUuid(), calorie, ingredientCategoryEnumIntegerMap, nutrientWeightMap,
                targetDate);

    }

    /**
     * 保存用户摄入信息
     * 
     * @param uuid 用户uuid
     * @param calorie 本次摄入热量
     * @param ingredientCategoryEnumIntegerMap 餐品食材分类重量，key: {@link IngredientCategoryEnum}，value: 食材重量
     * @param cuisineNutrientCodeWeightMap 餐品营养素重量列表，key: nutrient code {@link NutrientEnum}，value: 营养素重量
     * @param targetDate 添加记录到哪一天
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveIntakes(String uuid, double calorie,
            Map<IngredientCategoryEnum, Integer> ingredientCategoryEnumIntegerMap,
            Map<Integer, Double> cuisineNutrientCodeWeightMap, LocalDate targetDate) {
        /* 更新食材摄入历史记录 */
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, targetDate);
        if (userIngredientWeightSumDailyVo == null) {
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, targetDate);
        }
        CategoryModel cuisineIngredientCategoryWeightVo = ModelUtil
                .categoryEnumMapToModel(ingredientCategoryEnumIntegerMap, new CuisineIngredientCategoryWeightVo());
        userIngredientWeightSumDailyVo.addCuisineCategoryWeight(cuisineIngredientCategoryWeightVo, calorie);
        userIngredientWeightSumDailyService.insertOrUpdateByUuidAndDate(userIngredientWeightSumDailyVo);

        /* 更新营养素摄入历史记录 */
        List<UserNutrientWeightSumDailyVo> userNutrientWeightSumDailyVoList = userNutrientWeightSumDailyService
                .queryByUuidAndDate(uuid, targetDate);
        Map<Integer, Double> userNutrientCodeWeightSumDailyMap = Collections.emptyMap();
        if (!CollectionUtils.isEmpty(userNutrientWeightSumDailyVoList)) {
            userNutrientCodeWeightSumDailyMap = userNutrientWeightSumDailyVoList.stream().collect(Collectors
                    .toMap(UserNutrientWeightSumDailyVo::getNutrientCode, UserNutrientWeightSumDailyVo::getWeight));
        }
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
                        .nutrientCode(entry.getKey()).weight(entry.getValue()).date(targetDate).build())
                .collect(Collectors.toList());
        userNutrientWeightSumDailyService.replaceAll(uuid, targetDate, newUserNutrientWeightSumDailyVoList);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveCuisineHistory(String uuid, String cuisineCode, LocalDate currentHistoryDate) {
        /* 更新餐品记录 */
        userHistoricalCuisineService.add(UserHistoricalCuisineVo.builder().uuid(uuid).cuisineCode(cuisineCode)
                .status(CuisineTasteEnum.UNEVALUATED.getCode()).build());
        /* 餐品食材分类重量 */
        CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightService
                .queryByCuisineCode(cuisineCode);
        if (cuisineIngredientCategoryWeightVo == null) {
            throw new NutritionServiceException(
                    "Cuisine ingredient category weight can not be null, cuisine code " + cuisineCode);
        }
        /* 餐品热量 */
        double calorie = cuisineIngredientCategoryWeightVo.getCalorie();
        /* 餐品营养素含量 */
        List<CuisineNutrientWeightVo> cuisineNutrientWeightVoList = cuisineNutrientWeightService
                .queryByCuisineCode(cuisineCode);
        if (CollectionUtils.isEmpty(cuisineNutrientWeightVoList)) {
            throw new NutritionServiceException(
                    "Cuisine nutrient weight can not be empty, cuisine code " + cuisineCode);
        }
        Map<Integer, Double> collect = cuisineNutrientWeightVoList.stream().collect(
                Collectors.toMap(CuisineNutrientWeightVo::getNutrientCode, CuisineNutrientWeightVo::getWeight));
        this.saveIntakes(uuid, calorie, ModelUtil.modelToCategoryEnumMap(cuisineIngredientCategoryWeightVo), collect,
                currentHistoryDate);
    }

    public List<CuisinePreviewAo> queryTodayCuisineHistory(String uuid) {

        List<UserHistoricalOrderVo> userHistoricalOrderVoList = userHistoricalOrderService.queryByUuid(uuid, 5);
        List<Long> userHistoricalOrderIdList = userHistoricalOrderVoList.stream().map(UserHistoricalOrderVo::getId)
                .collect(Collectors.toList());

        List<HistoricalCuisineRecordVo> allHistoricalCuisineRecordList = new ArrayList<>();
        allHistoricalCuisineRecordList.addAll(userHistoricalCuisineService.queryLastByUuidLimit(uuid, 5));
        allHistoricalCuisineRecordList.addAll(userHistoricalOrderVoList);

        if (CollectionUtils.isEmpty(allHistoricalCuisineRecordList)) {
            return Collections.emptyList();
        }
        List<String> allCuisineCodeList = allHistoricalCuisineRecordList.stream()
                .map(HistoricalCuisineRecordVo::getCuisineCode)
                .collect(Collectors.toList());
        List<CuisineVo> cuisineVoList = cuisineService.queryByCuisineCodeList(allCuisineCodeList);
        List<StoreVo> storeVoList = storeService
                .queryByCodeList(cuisineVoList.stream().map(CuisineVo::getStoreCode).collect(Collectors.toList()));
        Map<String, StoreVo> storeCodeMap = storeVoList.stream()
                .collect(Collectors.toMap(StoreVo::getCode, Function.identity()));
        Map<String, CuisineVo> cuisineCodeMap = cuisineVoList.stream()
                .collect(Collectors.toMap(CuisineVo::getCode, Function.identity()));
        List<CuisineIngredientRelVo> cuisineIngredientRelVoList = cuisineIngredientRelService
                .queryByCuisineCodeList(allCuisineCodeList);
        Map<Integer, IngredientVo> ingredientCodeNameMap = ingredientService
                .queryByCodeList(cuisineIngredientRelVoList.stream().map(CuisineIngredientRelVo::getIngredientCode)
                        .distinct().collect(Collectors.toList()))
                .stream().collect(Collectors.toMap(IngredientVo::getCode, Function.identity()));
        Map<String, List<CuisineIngredientRelVo>> cuisineIngredientMap = cuisineIngredientRelVoList.stream()
                .collect(Collectors.groupingBy(CuisineIngredientRelVo::getCuisineCode));
        List<CuisinePreviewAo> cuisinePreviewAoList = Lists.newArrayList();
        LocalDate today = LocalDate.now();

        for (HistoricalCuisineRecordVo historicalCuisineVo : allHistoricalCuisineRecordList) {
            CuisineVo cuisineVo = cuisineCodeMap.get(historicalCuisineVo.getCuisineCode());
            StoreVo storeVo = storeCodeMap.get(cuisineVo.getStoreCode());
            List<CuisineIngredientRelVo> ingredientRelVoList = cuisineIngredientMap.getOrDefault(cuisineVo.getCode(),
                    Collections.emptyList());
            List<IngredientVo> mainIngredientList = ingredientRelVoList.stream()
                    .sorted(Comparator.comparingInt(CuisineIngredientRelVo::getWeight).reversed())
                    .filter(cuisineIngredientRelVo -> ingredientCodeNameMap
                            .containsKey(cuisineIngredientRelVo.getIngredientCode()))
                    .map(cuisineIngredientRelVo -> ingredientCodeNameMap
                            .get(cuisineIngredientRelVo.getIngredientCode()))
                    .collect(Collectors.toList());
            cuisinePreviewAoList.add(CuisinePreviewAo.builder().code(cuisineVo.getCode()).name(cuisineVo.getName())
                    .calorie(cuisineVo.getCalorie()).sortPriority(cuisineVo.getSortPriority())
                    .meituanSearchingName(cuisineVo.getMeituanSearchingName())
                    .lastAddedDateTimeStr(DateTimeUtil.todayOrLastDayFormat(today, historicalCuisineVo.getCreateTime()))
                    .lastAddedDateTime(DateTimeUtil.todayOrLastDayFormat(today, historicalCuisineVo.getCreateTime()))
                    .createTime(DateTimeUtil.convert(historicalCuisineVo.getCreateTime()))
                    .cuisineHistoryId(historicalCuisineVo.getId())
                    .orderRecord(userHistoricalOrderIdList.contains(historicalCuisineVo.getId()))
                    .storeCode(storeVo.getCode()).storeName(storeVo.getName())
                    .mainIngredientListStr(CuisineUtil.ingredientListToStr(mainIngredientList)).build());
        }

        return cuisinePreviewAoList.stream()
                .sorted(Comparator.comparing(CuisinePreviewAo::getCreateTime).reversed())
                .collect(Collectors.toList());
    }

    public UserInfoAo queryUserInfo(String uuid) {
        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        return UserInfoAo.builder().uuid(uuid).age(userInfoVo.getAge()).gender(userInfoVo.getGender())
                .goal(userInfoVo.getGoal()).height(userInfoVo.getHeight()).weight(userInfoVo.getWeight())
                .profeChar(userInfoVo.getProfeChar()).sportsHabits(userInfoVo.getSportsHabits()).build();
    }

    public String queryUuidByWechatCode(String wechatCode) {
        String openid = wechatHttpApiService.getUserOpenId(wechatCode);
        UserAccountVo userAccountVo = userAccountService.queryByExternalIdAndType(openid,
                UserAccountTypeEnum.WEI_XIN);
        if (userAccountVo == null) {
            userAccountVo = creatAccountUserInfoAndModel(openid);
        }
        return userAccountVo.getUuid();
    }

}
