package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.controller.ao.CuisineDetailsAo;
import com.nutrition.nutritionservice.controller.ao.UserOrderRecordDetailsAo;
import com.nutrition.nutritionservice.enums.database.UserHistoricalOrderStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.UserHistoricalOrderService;
import com.nutrition.nutritionservice.util.DateTimeUtil;
import com.nutrition.nutritionservice.vo.UserHistoricalOrderVo;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * @author heng.liu
 * @since 2021/2/24
 */
@Biz
public class UserHistoricalOrderBiz {

    @Resource
    private UserHistoricalOrderService userHistoricalOrderService;

    @Resource
    private UserBiz userBiz;

    @Resource
    private CuisineBiz cuisineBiz;

    public void saveHistoricalOrder(String uuid, String cuisineCode) {
        userHistoricalOrderService.add(uuid, cuisineCode);
    }

    public void deleteHistoricalOrder(Long id) {
        if (id == null) {
            return;
        }
        userHistoricalOrderService.updateStatusByPrimaryKey(id, UserHistoricalOrderStatusEnum.DELETED.getCode());
    }

    @Transactional(rollbackFor = Exception.class)
    public void addHistoricalOrderToRecord(Long orderHistoryId) {
        if (orderHistoryId == null) {
            return;
        }
        UserHistoricalOrderVo userHistoricalOrderVo = userHistoricalOrderService.queryById(orderHistoryId);
        if (userHistoricalOrderVo == null) {
            throw new NutritionServiceException("未找到用户点餐记录，orderHistoryId " + orderHistoryId);
        }
        userBiz.saveCuisineHistory(userHistoricalOrderVo.getUuid(), userHistoricalOrderVo.getCuisineCode(),
                userHistoricalOrderVo.getCreateTime().toLocalDate());
        userHistoricalOrderService.updateStatusByPrimaryKey(orderHistoryId,
                UserHistoricalOrderStatusEnum.YET.getCode());
    }

    public UserOrderRecordDetailsAo queryOrderRecordId(Long orderHistoryId) {
        UserHistoricalOrderVo userHistoricalOrderVo = userHistoricalOrderService.queryById(orderHistoryId);
        if (userHistoricalOrderVo == null) {
            throw new NutritionServiceException("未找到用户点餐记录，id " + orderHistoryId);
        }
        CuisineDetailsAo cuisineDetails = cuisineBiz.queryCuisineDetails(userHistoricalOrderVo.getCuisineCode());
        return UserOrderRecordDetailsAo.builder().orderRecordId(orderHistoryId).uuid(userHistoricalOrderVo.getUuid())
                .createTimeStr(
                        DateTimeUtil.todayOrLastDayFormat(LocalDate.now(), userHistoricalOrderVo.getCreateTime()))
                .cuisineDetails(cuisineDetails).build();
    }
}
