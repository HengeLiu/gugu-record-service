package com.nutrition.nutritionservice.biz;

import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.enums.database.UserHistoricalOrderStatusEnum;
import com.nutrition.nutritionservice.exception.NutritionServiceException;
import com.nutrition.nutritionservice.service.UserHistoricalOrderService;
import com.nutrition.nutritionservice.vo.UserHistoricalOrderVo;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

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
    public void addHistoricalOrderToRecord(Long id) {
        if (id == null) {
            return;
        }
        UserHistoricalOrderVo userHistoricalOrderVo = userHistoricalOrderService.queryById(id);
        if (userHistoricalOrderVo == null) {
            throw new NutritionServiceException("未找到用户点餐记录，id " + id);
        }
        userBiz.saveCuisineHistory(userHistoricalOrderVo.getUuid(), userHistoricalOrderVo.getCuisineCode(),
                userHistoricalOrderVo.getCreateTime().toLocalDate());
        userHistoricalOrderService.updateStatusByPrimaryKey(id, UserHistoricalOrderStatusEnum.YET.getCode());
    }

}
