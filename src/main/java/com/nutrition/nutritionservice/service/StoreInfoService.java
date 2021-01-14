package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.StoreInfoDao;
import com.nutrition.nutritionservice.vo.StoreInfoVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2021/1/12
 */
@Service
public class StoreInfoService {

    @Resource
    private StoreInfoDao storeInfoDao;

    public List<StoreInfoVo> queryAll() {
        return storeInfoDao.selectAll();
    }

    public List<StoreInfoVo> queryByStatus(int storeStatus) {
        return storeInfoDao.selectByStatus(storeStatus);
    }

}
