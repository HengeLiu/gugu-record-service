package com.nutrition.nutritionservice;

import com.nutrition.nutritionservice.biz.CuisineBiz;
import com.nutrition.nutritionservice.enums.database.DineTimeEnum;
import com.nutrition.nutritionservice.service.CuisineService;
import com.nutrition.nutritionservice.service.UserCategoryIntakesModelService;
import com.nutrition.nutritionservice.service.UserHistoricalCuisineService;
import com.nutrition.nutritionservice.service.UserHistoricalWeightSumDailyService;
import com.nutrition.nutritionservice.service.UserInfoService;
import com.nutrition.nutritionservice.vo.CuisineRecommendedScoreWebAo;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;
import com.nutrition.nutritionservice.vo.user.UserCategoryIntakesModelVo;
import com.nutrition.nutritionservice.vo.user.UserInfoVo;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.List;

/**
 * 用户模型测试
 *
 * @author heng.liu
 * @since 2020/12/29
 */
@SpringBootTest
@Slf4j
public class UserModelTest {

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

    @Test
    public void createUserHistorical() {
        DineTimeEnum dineTimeEnum = DineTimeEnum.BREAKFAST;
        // DineTimeEnum dineTimeEnum = DineTimeEnum.LUNCH;
        // DineTimeEnum dineTimeEnum = DineTimeEnum.DINNER;
        String testUuid = "" + (100000 + (int) (Math.random() * 103));
        UserInfoVo userInfo = userInfoService.selectByUuid(testUuid);
        log.info(userInfo.toString());
        UserCategoryIntakesModelVo userCategoryIntakesModel = userCategoryIntakesModelService
                .querySelectLastByUuid(testUuid);
        List<CuisineRecommendedScoreWebAo> dineTimeRecommendedCuisineList = cuisineBiz
                .queryRecommendedCuisineListByDineTime(userCategoryIntakesModel, dineTimeEnum.getCode(),
                        IDPageParamVo.builder().pageNumber(1).rowNumber(200).build());
        UserHistoricalWeightSumDailyVo userHistoricalWeightSumDaily = userHistoricalWeightSumDailyService
                .queryByUuidAndDate(testUuid, LocalDate.now());
        userHistoricalWeightSumDaily = userHistoricalWeightSumDaily == null
                ? UserHistoricalWeightSumDailyVo.createEmpty(testUuid, LocalDate.now())
                : userHistoricalWeightSumDaily;
        List<CuisineRecommendedScoreWebAo> historicalRecommendedCuisineList = cuisineBiz
                .queryRecommendedCuisineListByHistorical(userCategoryIntakesModel, userHistoricalWeightSumDaily,
                        IDPageParamVo.builder().pageNumber(1).rowNumber(200).build());
        log.info("");

    }

}
