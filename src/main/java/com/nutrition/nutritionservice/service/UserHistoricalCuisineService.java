package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserHistoricalCuisineDao;
import com.nutrition.nutritionservice.enums.database.UserHistoricalCuisineStatusEnum;
import com.nutrition.nutritionservice.vo.user.UserHistoricalCuisineVo;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

/**
 * 用户历史菜品记录Service。
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Service
public class UserHistoricalCuisineService {

    @Resource
    private UserHistoricalCuisineDao userHistoricalCuisineDao;

    public int countByCuisineCodeAndTaste(String cuisineCode, int taste) {
        return userHistoricalCuisineDao.countByCuisineCodeAndTaste(cuisineCode, taste);
    }

    @Nullable
    public UserHistoricalCuisineVo queryLastAddedCuisine(String uuid) {
        return userHistoricalCuisineDao.selectLastCuisineByUuid(uuid);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(UserHistoricalCuisineVo userHistoricalCuisineVo) {
        userHistoricalCuisineDao.insert(userHistoricalCuisineVo);
    }

    @Nullable
    @Transactional(rollbackFor = Exception.class)
    public UserHistoricalCuisineVo remove(long userHistoricalCuisineId) {
        userHistoricalCuisineDao.updateStatusById(userHistoricalCuisineId,
                UserHistoricalCuisineStatusEnum.DELETED.getCode());
        return userHistoricalCuisineDao.selectById(userHistoricalCuisineId);
    }

    public List<UserHistoricalCuisineVo> queryByUuidAndDate(String uuid, LocalDate localDate) {
        return userHistoricalCuisineDao.selectByUuidAndDateTime(uuid, localDate.atTime(LocalTime.MIN),
                localDate.atTime(LocalTime.MAX));
    }

}
