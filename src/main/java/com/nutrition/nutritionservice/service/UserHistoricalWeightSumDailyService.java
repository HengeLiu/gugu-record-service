package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserHistoricalWeightSumDailyDao;
import com.nutrition.nutritionservice.vo.user.UserHistoricalWeightSumDailyVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDate;

/**
 * 用户历史摄入重量每日汇总。
 *
 * @author heng.liu
 * @since 2020/12/29
 */
@Service
public class UserHistoricalWeightSumDailyService {

    @Resource
    private UserHistoricalWeightSumDailyDao userHistoricalWeightSumDailyDao;

    public UserHistoricalWeightSumDailyVo queryByUuidAndDate(String uuid, LocalDate date) {
        return userHistoricalWeightSumDailyDao.selectByUuidAndDate(uuid, date);
    }

    public void insertOrUpdateByUuidAndDate(UserHistoricalWeightSumDailyVo record) {
        if (userHistoricalWeightSumDailyDao.selectByUuidAndDate(record.getUuid(), record.getDate()) == null) {
            userHistoricalWeightSumDailyDao.insert(record);
        } else {
            userHistoricalWeightSumDailyDao.updateByUuidAndDate(record);
        }
    }

}
