package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineIngredientRelDao;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
import java.util.List;

/**
 * 菜品食材关系Service。
 *
 * @author heng.liu
 * @since 2020/12/29
 */
@Service
public class CuisineIngredientRelService {

    @Resource
    private CuisineIngredientRelDao cuisineIngredientRelDao;

    @Transactional(rollbackFor = Exception.class)
    public void add(CuisineIngredientRelVo cuisineIngredientRelVo) {
        cuisineIngredientRelDao.insert(cuisineIngredientRelVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public int addBatch(List<CuisineIngredientRelVo> relList) {
        if (CollectionUtils.isEmpty(relList)){
            return 0;
        }
        return cuisineIngredientRelDao.batchInsert(relList);
    }

    @Transactional(rollbackFor = Exception.class)
    public int replaceAllByCuisineCode(String cuisineCode, List<CuisineIngredientRelVo> relList) {
        cuisineIngredientRelDao.deleteByCuisineCode(cuisineCode);
        return this.addBatch(relList);
    }

    public List<CuisineIngredientRelVo> queryByCuisineCode(String cuisineCode) {
        return cuisineIngredientRelDao.selectByCuisineCode(cuisineCode);
    }

    public List<CuisineIngredientRelVo> queryByCuisineCodeList(List<String> cuisineCodeList) {
        if (CollectionUtils.isEmpty(cuisineCodeList)){
            return Collections.emptyList();
        }
        return cuisineIngredientRelDao.batchSelectByCuisineCodeList(cuisineCodeList);
    }

}
