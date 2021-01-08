package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineIngredientRelDao;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
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

    public void add(CuisineIngredientRelVo cuisineIngredientRelVo) {
        cuisineIngredientRelDao.insert(cuisineIngredientRelVo);
    }

    public int addBatch(List<CuisineIngredientRelVo> relList) {
        return cuisineIngredientRelDao.batchInsert(relList);
    }

    public List<CuisineIngredientRelVo> queryByCuisineCode(String cuisineCode) {
        return cuisineIngredientRelDao.selectByCuisineCode(cuisineCode);
    }

    public List<CuisineIngredientRelVo> queryByCuisineCodeList(List<String> cuisineCodeList) {
        return cuisineIngredientRelDao.batchSelectByCuisineCodeList(cuisineCodeList);
    }

}
