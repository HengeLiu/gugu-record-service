package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.enums.database.CuisineTasteEnum;
import com.nutrition.nutritionservice.enums.database.DineTimeEnum;
import com.nutrition.nutritionservice.enums.database.UserHistoricalCuisineStatusEnum;
import com.nutrition.nutritionservice.service.CuisineCategoryWeightService;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserHistoricalWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.util.ModelUtil;
import com.nutrition.nutritionservice.util.VectorUtil;
import com.nutrition.nutritionservice.vo.CuisineCategoryWeightVo;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
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
    private UserHistoricalWeightSumDailyService userHistoricalWeightSumDailyService;

    @Resource
    private CuisineService cuisineService;

    @Resource
    private CuisineBiz cuisineBiz;

    @Resource
    private UserCategoryIntakesModelService userCategoryIntakesModelService;

    @Resource
    private UserInfoService userInfoService;

    @Resource
    private CuisineCategoryWeightService cuisineCategoryWeightService;

    @Test
    public void testHistorical() {
        String testUuid = "" + (100000 + (int) (Math.random() * 103));
        UserInfoVo userInfo = userInfoService.selectByUuid(testUuid);
        LocalDate date = LocalDate.now();
        log.info(userInfo.toString());
        UserCategoryIntakesModelVo userCategoryIntakesModel = userCategoryIntakesModelService
                .querySelectLastByUuid(testUuid);
        UserHistoricalWeightSumDailyVo userHistoricalWeightSumDaily = userHistoricalWeightSumDailyService
                .queryByUuidAndDate(testUuid, LocalDate.now());
        userHistoricalWeightSumDaily = userHistoricalWeightSumDaily == null
                ? UserHistoricalWeightSumDailyVo.createEmpty(testUuid, date)
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
        UserCategoryIntakesModelVo userCategoryIntakesModel = userCategoryIntakesModelService
                .querySelectLastByUuid(testUuid);
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
                    .cuisineCode(mostRecommendedCuisine.getCode()).tasteScore(CuisineTasteEnum.UNKNOWN.getCode())
                    .status(UserHistoricalCuisineStatusEnum.NORMAL.getCode()).build());
            UserHistoricalWeightSumDailyVo userHistoricalWeightSumDailyVo = userHistoricalWeightSumDailyService
                    .queryByUuidAndDate(testUuid, date);
            userHistoricalWeightSumDailyVo = userHistoricalWeightSumDailyVo == null
                    ? UserHistoricalWeightSumDailyVo.createEmpty(testUuid, date)
                    : userHistoricalWeightSumDailyVo;
            // 用户今日摄入记录向量
            Vector<Double> userHistoricalWeightVector = ModelUtil.modelToVector(userHistoricalWeightSumDailyVo);
            log.info("用户今日摄入记录向量 {}", userHistoricalWeightVector.toString());
            CuisineCategoryWeightVo cuisineCategoryWeightVo = cuisineCategoryWeightService
                    .queryByCuisineCode(mostRecommendedCuisine.getCode());
            // 菜品食材种类重量向量
            Vector<Integer> cuisineCategoryWeightVector = ModelUtil.modelToVector(cuisineCategoryWeightVo);
            log.info("菜品食材种类重量向量 {}", cuisineCategoryWeightVector.toString());
            ModelUtil.vectorToModel(VectorUtil.addition(cuisineCategoryWeightVector, userHistoricalWeightVector),
                    userHistoricalWeightSumDailyVo);
            userHistoricalWeightSumDailyService.insertOrUpdateByUuidAndDate(userHistoricalWeightSumDailyVo);
            log.info("用户摄入后模型 {}", ModelUtil.modelToVector(userHistoricalWeightSumDailyVo).toString());

        }

    }

}
