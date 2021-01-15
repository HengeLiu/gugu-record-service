package com.nutrition.nutritionservice.biz.record;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.biz.health.ModelIngredientCategoryModelBiz;
import com.nutrition.nutritionservice.biz.health.UserNutrientWeightSumDailyBiz;
import com.nutrition.nutritionservice.controller.ao.PreloadDataAo;
import com.nutrition.nutritionservice.controller.ao.StoreRecommendAo;
import com.nutrition.nutritionservice.controller.ao.UserOpinionAo;
import com.nutrition.nutritionservice.controller.health.ao.NutrientWeightAo;
import com.nutrition.nutritionservice.converter.Model2UserModelConverter;
import com.nutrition.nutritionservice.enums.database.ProductFunctionStatusEnum;
import com.nutrition.nutritionservice.enums.database.StoreStatusEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountStatusTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserAccountTypeEnum;
import com.nutrition.nutritionservice.enums.database.UserIngredientModelStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import com.nutrition.nutritionservice.service.ProductFunctionService;
import com.nutrition.nutritionservice.service.ProductStoreRecommendationService;
import com.nutrition.nutritionservice.service.StoreInfoService;
import com.nutrition.nutritionservice.service.UserAccountService;
import com.nutrition.nutritionservice.service.UserFunctionVotesService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.ProductUserRecommendationService;
import com.nutrition.nutritionservice.service.UserStatusInfoService;
import com.nutrition.nutritionservice.service.WechatHttpApiService;
import com.nutrition.nutritionservice.vo.ProductFunctionVo;
import com.nutrition.nutritionservice.vo.ProductStoreRecommendationVo;
import com.nutrition.nutritionservice.vo.StoreInfoVo;
import com.nutrition.nutritionservice.vo.UserFunctionVotesVo;
import com.nutrition.nutritionservice.vo.ProductUserRecommendationVo;
import com.nutrition.nutritionservice.vo.UserStatusInfoVo;
import com.nutrition.nutritionservice.vo.user.UserAccountVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections4.CollectionUtils;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
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
public class ProgramBiz {

    @Resource
    private UserAccountService userAccountService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private UserIngredientWeightSumDailyService userIngredientWeightSumDailyService;

    @Resource
    private UserStatusInfoService userStatusInfoService;

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @Resource
    private ModelIngredientCategoryModelBiz modelIngredientCategoryModelBiz;

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
    public PreloadDataAo loadUserInfo(String openid) {
        PreloadDataAo.PreloadDataAoBuilder preloadDataAoBuilder = PreloadDataAo.builder();
        UserInfoVo userInfoVo;
        UserIngredientCategoryModelVo userIngredientCategoryModelVo;
        UserStatusInfoVo userStatusInfoVo;
        String uuid;
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
                    modelIngredientCategoryModelBiz.calculateIngredientModel(userInfoVo), uuid,
                    UserIngredientModelStatusEnum.ACTIVE);
            // 创建用户模型
            long userModelId = userIngredientCategoryModelService.add(userIngredientCategoryModelVo);
            userIngredientCategoryModelVo.setId(userModelId);

            userInfoVo.setUuid(uuid);
            userInfoVo.setActiveModelId(userModelId);
            userInfoVo.setCalorie(userIngredientCategoryModelVo.getTargetCalorie());
            // 创建用户信息
            userInfoService.add(userInfoVo);

            userStatusInfoVo = UserStatusInfoVo.builder().uuid(uuid).customInfo(0).shownInfoCollectWindow(0)
                    .shownProcessWindow(0).build();
            // 创建用户状态
            userStatusInfoService.add(userStatusInfoVo);

        } else {
            uuid = userAccount.getUuid();
            userInfoVo = userInfoService.selectByUuid(uuid);

        }

        preloadDataAoBuilder.uuid(uuid);
        preloadDataAoBuilder.modelCalorie(userInfoVo.getCalorie());

        /* 用户食材分类模型目标值及历史摄入量 */
        // 获取用户食材分类今日摄入历史
        UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                .queryByUuidAndDate(uuid, LocalDate.now());
        if (userIngredientWeightSumDailyVo == null) {
            userIngredientWeightSumDailyVo = UserIngredientWeightSumDailyVo.createEmpty(uuid, LocalDate.now());
        }

        double historicalCalorie = userIngredientWeightSumDailyVo.getCalorie();
        preloadDataAoBuilder.historicalCalorieDaily(historicalCalorie);

        /* 获取用户营养素今日摄入历史 */
        List<NutrientWeightAo> nutrientWeightList = userNutrientWeightSumDailyBiz.queryUserNutrientWeightSumDaily(uuid,
                historicalCalorie, LocalDate.now());
        preloadDataAoBuilder.userNutrientHistoricalIntakesDaily(nutrientWeightList);

        List<UserFunctionVotesVo> userFunctionVotesVoList = userFunctionVotesService.queryByUuidAndDate(uuid,
                LocalDate.now());
        preloadDataAoBuilder.todayPushingTime(CollectionUtils.size(userFunctionVotesVoList.size()));

        List<StoreInfoVo> storeInfoVoList = storeInfoService.queryByStatus(StoreStatusEnum.ONLINE.getCode(), 3);
        preloadDataAoBuilder
                .storeIconUrlList(storeInfoVoList.stream().map(StoreInfoVo::getIconUrl).collect(Collectors.toList()));

        return preloadDataAoBuilder.build();
    }

    public PreloadDataAo loadUserInfoByWxCodeCode(String jsCode) {
        String wxOpenid = wechatHttpApiService.getUserOpenId(jsCode);
        if (StringUtils.isEmpty(wxOpenid)) {
            log.error("Cannot get wx.openid.");
            throw new NutritionServiceException("未查询到用户的微信账号");
        }
        return loadUserInfo(wxOpenid);
    }

    public List<ProductFunctionVo> queryNominatedFunctionList() {
        return productFunctionService.queryProductFunctionList(ProductFunctionStatusEnum.NOMINATED.getCode());
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
