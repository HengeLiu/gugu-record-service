package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.DineTimeEnum;
import com.nutrition.nutritionservice.enums.database.UserHistoricalCuisineStatusEnum;
import com.nutrition.nutritionservice.service.CuisineIngredientCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.UserIngredientCategoryModelService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserIngredientWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.util.VectorUtil;
import com.nutrition.nutritionservice.vo.CuisineIngredientCategoryWeightVo;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.CuisineVo;
import com.nutrition.nutritionservice.vo.user.UserIngredientCategoryModelVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;
import java.util.Vector;

/**
 * 菜品推荐测试
 *
 * @author heng.liu
 * @since 2020/12/29
 */
@SpringBootTest
@Slf4j
public class CuisineRecommendedTest {

    @Resource
    private UserHistoricalCuisineService userHistoricalCuisineService;

    @Resource
    private UserIngredientWeightSumDailyService userIngredientWeightSumDailyService;

    @Resource
    private CuisineService cuisineService;

    @Resource
    private CuisineBiz cuisineBiz;

    @Resource
    private UserIngredientCategoryModelService userIngredientCategoryModelService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private CuisineIngredientCategoryWeightService cuisineIngredientCategoryWeightService;

    @Test
    public void testHistorical() {
        String testUuid = "" + (100000 + (int) (Math.random() * 103));
        UserInfoVo userInfo = userInfoService.selectByUuid(testUuid);
        LocalDate date = LocalDate.now();
        log.info(userInfo.toString());
        UserIngredientCategoryModelVo userCategoryIntakesModel = userIngredientCategoryModelService
                .queryById(userInfo.getActiveModelId());
        UserIngredientWeightSumDailyVo userHistoricalWeightSumDaily = userIngredientWeightSumDailyService
                .queryByUuidAndDate(testUuid, LocalDate.now());
        userHistoricalWeightSumDaily = userHistoricalWeightSumDaily == null
                ? UserIngredientWeightSumDailyVo.createEmpty(testUuid, date)
                : userHistoricalWeightSumDaily;
        List<CuisineRecommendedScoreWebAo> historicalRecommendedCuisineList = cuisineBiz
                .queryRecommendedCuisineListByHistorical(userCategoryIntakesModel, userHistoricalWeightSumDaily,
                        IDPageParamVo.builder().pageNumber(1).rowNumber(200).build());
    }

    @Test
    public void testDine() {
        String testUuid = "" + (100000 + (int) (Math.random() * 103));
        UserInfoVo userInfo = userInfoService.selectByUuid(testUuid);
        log.info(userInfo.toString());
        LocalDate date = LocalDate.now();
        UserIngredientCategoryModelVo userCategoryIntakesModel = userIngredientCategoryModelService
                .queryById(userInfo.getId());
        // 用户目标模型向量
        Vector<Integer> userModelVector = ModelUtil.modelToVector(userCategoryIntakesModel);
        log.info(userModelVector.toString());
        for (DineTimeEnum dine : DineTimeEnum.values()) {
            if (dine == DineTimeEnum.UNKNOWN) {
                continue;
            }
            List<CuisineRecommendedScoreWebAo> dineTimeRecommendedCuisineList = cuisineBiz
                    .queryRecommendedCuisineListByDineTime(userCategoryIntakesModel, dine.getCode(),
                            IDPageParamVo.builder().pageNumber(1).rowNumber(200).build());
            CuisineVo mostRecommendedCuisine = cuisineService
                    .queryByCuisineCode(dineTimeRecommendedCuisineList.get(0).getCuisineCode());
            userHistoricalCuisineService.add(UserHistoricalCuisineVo.builder().uuid(testUuid)
                    .cuisineCode(mostRecommendedCuisine.getCode()).tasteScore(CuisineTasteEnum.UNEVALUATED.getCode())
                    .status(UserHistoricalCuisineStatusEnum.NORMAL.getCode()).build());
            UserIngredientWeightSumDailyVo userIngredientWeightSumDailyVo = userIngredientWeightSumDailyService
                    .queryByUuidAndDate(testUuid, date);
            userIngredientWeightSumDailyVo = userIngredientWeightSumDailyVo == null
                    ? UserIngredientWeightSumDailyVo.createEmpty(testUuid, date)
                    : userIngredientWeightSumDailyVo;
            // 用户今日摄入记录向量
            Vector<Integer> userHistoricalWeightVector = ModelUtil.modelToVector(userIngredientWeightSumDailyVo);
            log.info("用户今日摄入记录向量 {}", userHistoricalWeightVector.toString());
            CuisineIngredientCategoryWeightVo cuisineIngredientCategoryWeightVo = cuisineIngredientCategoryWeightService
                    .queryByCuisineCode(mostRecommendedCuisine.getCode());
            // 菜品食材种类重量向量
            Vector<Integer> cuisineCategoryWeightVector = ModelUtil.modelToVector(cuisineIngredientCategoryWeightVo);
            log.info("菜品食材种类重量向量 {}", cuisineCategoryWeightVector.toString());
            ModelUtil.vectorToModel(VectorUtil.additionInt(cuisineCategoryWeightVector, userHistoricalWeightVector),
                    userIngredientWeightSumDailyVo);
            userIngredientWeightSumDailyService.insertOrUpdateByUuidAndDate(userIngredientWeightSumDailyVo);
            log.info("用户摄入后模型 {}", ModelUtil.modelToVector(userIngredientWeightSumDailyVo).toString());

        }

    }

}
