package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.UserHistoricalOrderDao;
import com.nutrition.nutritionservice.vo.UserHistoricalOrderVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2021/2/24
 */
@Service
public class UserHistoricalOrderService {

    @Resource
    private UserHistoricalOrderDao userHistoricalOrderDao;

    public UserHistoricalOrderVo queryLastByUuid(String uuid) {
        return userHistoricalOrderDao.selectLastByUuid(uuid);
    }

    public UserHistoricalOrderVo queryById(long id) {
        return userHistoricalOrderDao.selectById(id);
    }

    public List<UserHistoricalOrderVo> queryByUuid(String uuid, int limit) {
        return userHistoricalOrderDao.selectByUuid(uuid, limit);
    }

    @Transactional(rollbackFor = Exception.class)
    public int add(String uuid, String cuisineCode) {
        return userHistoricalOrderDao.insert(uuid, cuisineCode);
    }

    @Transactional(rollbackFor = Exception.class)
    public int updateStatusByPrimaryKey(long id, int status) {
        return userHistoricalOrderDao.updateStatusById(id, status);
    }

}
