package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineHistoricalTasteDao;
import com.nutrition.nutritionservice.vo.CuisineHistoricalTasteVo;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 菜品历史口味评价汇总Service。
 *
 * @author heng.liu
 * @since 2020/12/28
 */
@Service
public class CuisineHistoricalTasteService {

    @Resource
    private CuisineHistoricalTasteDao cuisineHistoricalTasteDao;

    public List<CuisineHistoricalTasteVo> queryByCuisineCodeList(List<String> cuisineCodeList) {
        if (CollectionUtils.isEmpty(cuisineCodeList)){
            return Collections.emptyList();
        }
        return cuisineHistoricalTasteDao.batchElectByCuisineCode(cuisineCodeList);
    }

}
