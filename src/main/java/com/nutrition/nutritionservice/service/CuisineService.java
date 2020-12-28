package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineDao;
import com.nutrition.nutritionservice.dao.CuisineIngredientRelDao;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.store.CuisineAssemblyAo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * 菜品 service
 *
 * @author heng.liu
 * @since 2020/12/23
 */
@Service
public class CuisineService {

    @Resource
    private CuisineDao cuisineDao;

    @Resource
    private CuisineIngredientRelDao cuisineIngredientRelDao;

    /**
     * 统计指定热量返回和目标的菜品数量。
     * 
     * @param minCalorie 热量最低值，包含。
     * @param maxCalorie 热量最高值，不包含。
     * @param goal 菜品推荐目标。
     */
    public int countByCalorieAndGoal(int minCalorie, int maxCalorie, int goal) {
        return cuisineDao.selectCountByCalorieAndGoal(minCalorie, maxCalorie, goal);
    }

    @Transactional(rollbackFor = Exception.class)
    public void saveNewCuisine(CuisineAssemblyAo cuisineAssemblyAo) {
        cuisineDao.insert(cuisineAssemblyAo.getCuisineVo());
        cuisineIngredientRelDao.batchInsert(cuisineAssemblyAo.getCuisineIngredientRelList());
    }

    public List<CuisineVo> queryPageCuisineList(IDPageParamVo paramVo) {
        return cuisineDao.selectFromIdWithLimit(paramVo.getFirstRowNumber(), paramVo.getRowNumber());
    }

}
