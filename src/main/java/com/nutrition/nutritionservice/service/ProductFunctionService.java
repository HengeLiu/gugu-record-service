package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.ProductFunctionDao;
import com.nutrition.nutritionservice.vo.ProductFunctionVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author heng.liu
 * @since 2021/1/15
 */
@Service
public class ProductFunctionService {

    @Resource
    private ProductFunctionDao productFunctionDao;

    public List<ProductFunctionVo> queryProductFunctionList(int functionStatus) {
        return productFunctionDao.selectByStatus(functionStatus);
    }
}
