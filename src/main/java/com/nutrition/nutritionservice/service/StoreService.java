package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.StoreDao;
import com.nutrition.nutritionservice.vo.StoreVo;
import org.checkerframework.checker.nullness.qual.Nullable;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 门店
 *
 * @author heng.liu
 * @since 2021/1/8
 */
@Service
public class StoreService {

    @Resource
    private StoreDao storeDao;

    @Nullable
    public StoreVo queryByCode(String code) {
        return storeDao.selectByCode(code);
    }

}
