package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CustomCuisineTmpDao;
import com.nutrition.nutritionservice.vo.CustomCuisineTmpVo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户自定义餐品缓存Service
 *
 * @author heng.liu
 * @since 2021/5/3
 */
@Service
@Slf4j
public class CustomCuisineTmpService {

    @Resource
    private CustomCuisineTmpDao customCuisineTmpDao;

    public int add(CustomCuisineTmpVo customCuisineTmpVo) {
        return customCuisineTmpDao.insert(customCuisineTmpVo);
    }

}
