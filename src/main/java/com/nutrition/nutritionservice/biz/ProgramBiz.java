package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.NutrientWeightAo;
import com.nutrition.nutritionservice.controller.ao.PreloadDataAo;
import com.nutrition.nutritionservice.controller.ao.ProductFunctionListAo;
import com.nutrition.nutritionservice.controller.ao.StoreRecommendAo;
import com.nutrition.nutritionservice.controller.ao.UserOpinionAo;
import com.nutrition.nutritionservice.enums.database.ProductFunctionStatusEnum;
import com.nutrition.nutritionservice.enums.database.StoreStatusEnum;
import com.nutrition.nutritionservice.service.ProductFunctionService;
import com.nutrition.nutritionservice.service.ProductStoreRecommendationService;
import com.nutrition.nutritionservice.service.ProductUserRecommendationService;
import com.nutrition.nutritionservice.service.StoreInfoService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserFunctionVotesService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.vo.ProductStoreRecommendationVo;
import com.nutrition.nutritionservice.vo.ProductUserRecommendationVo;
import com.nutrition.nutritionservice.vo.StoreInfoVo;
import com.nutrition.nutritionservice.vo.UserFunctionVotesVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * 程序加载数据
 *
 * @author heng.liu
 * @since 2021/1/4
 */
@Biz
@Slf4j
public class ProgramBiz {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserIngredientWeightSumDailyService userIngredientWeightSumDailyService;

    @Resource
    private UserStatusInfoService userStatusInfoService;

    @Resource
    private UserNutrientWeightSumDailyBiz userNutrientWeightSumDailyBiz;

    @Resource
    private WechatHttpApiService wechatHttpApiService;

    @Resource
    private ProductFunctionService productFunctionService;

    @Resource
    private ProductUserRecommendationService productUserRecommendationService;

    @Resource
    private UserFunctionVotesService userFunctionVotesService;

    @Resource
    private StoreInfoService storeInfoService;

    @Resource
    private ProductStoreRecommendationService productStoreRecommendationService;

    @Transactional(rollbackFor = Exception.class)
    public PreloadDataAo loadUserInfoUuid(String uuid) {
        PreloadDataAo.PreloadDataAoBuilder preloadDataAoBuilder = PreloadDataAo.builder();
        preloadDataAoBuilder.uuid(uuid);


        UserInfoVo userInfoVo = userInfoService.selectByUuid(uuid);
        /* 用户目标摄入热量 */
        preloadDataAoBuilder.targetCalorie(userInfoVo.getTargetCalorie());

        /* 用户食材分类模型目标值及历史摄入量 */
        // 获取用户食材分类今日摄入历史
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userIngredientWeightSumDailyVo == null) {
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }

        /* 今日热量摄入历史 */
        double historicalCalorie = userIngredientWeightSumDailyVo.getCalorie();
        preloadDataAoBuilder.historicalCalorieDaily(historicalCalorie);

        /* 获取用户营养素今日摄入历史 */
        List<NutrientWeightAo> nutrientWeightList = userNutrientWeightSumDailyBiz.queryUserNutrientWeightSumDaily(uuid,
                historicalCalorie, LocalDate.now());
        preloadDataAoBuilder.userNutrientHistoricalIntakesDaily(nutrientWeightList);

        /* 需要展示图片的门店列表 */
        List<StoreInfoVo> storeInfoVoList = storeInfoService.queryByStatus(StoreStatusEnum.ONLINE.getCode(), 3);
        preloadDataAoBuilder
                .needIconStoreCodeList(
                        storeInfoVoList.stream().map(StoreInfoVo::getStoreCode).collect(Collectors.toList()));

        /* 用户状态 */
        UserStatusInfoVo userStatusInfoVo = userStatusInfoService.queryBuUuid(uuid);
        preloadDataAoBuilder.customInfo(Objects.requireNonNull(userStatusInfoVo).getCustomInfo());

        return preloadDataAoBuilder.build();
    }

    public ProductFunctionListAo queryNominatedFunctionList(String uuid) {
        /* 今日已催更次数 */
        List<UserFunctionVotesVo> userFunctionVotesVoList = userFunctionVotesService.queryByUuidAndDate(uuid,
                LocalDate.now());
        return ProductFunctionListAo.builder().userTodayPushingTime(CollectionUtils.size(userFunctionVotesVoList))
                .userTodayLastPushingCode(userFunctionVotesVoList.stream()
                        .sorted(Comparator.comparing(UserFunctionVotesVo::getCreateTime).reversed())
                        .map(UserFunctionVotesVo::getFunctionCode).findFirst().orElse(null))
                .productFunctionList(
                        productFunctionService.queryProductFunctionList(ProductFunctionStatusEnum.NOMINATED.getCode()))
                .build();

    }

    public void pushingFunction(String uuid, String functionCode) {
        userFunctionVotesService.add(UserFunctionVotesVo.builder().uuid(uuid).functionCode(functionCode).build());
    }

    public void saveOpinion(UserOpinionAo userOpinionAo) {
        productUserRecommendationService.add(ProductUserRecommendationVo.builder().uuid(userOpinionAo.getUuid())
                .contact(userOpinionAo.getContact()).content(userOpinionAo.getContent()).build());
    }

    public void saveStoreRecommendation(StoreRecommendAo storeRecommendAo) {
        productStoreRecommendationService.add(ProductStoreRecommendationVo.builder().uuid(storeRecommendAo.getUuid())
                .storeName(storeRecommendAo.getStoreName()).userAddress(storeRecommendAo.getUserAddress()).build());
    }

}
