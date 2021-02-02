package com.nutrition.nutritionservice.service;

import com.nutrition.nutritionservice.dao.CuisineDao;
import com.nutrition.nutritionservice.vo.IDPageParamVo;
import com.nutrition.nutritionservice.vo.store.CuisineVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import javax.annotation.Resource;
import java.util.Collections;
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

    /**
     * 统计指定热量返回和目标的菜品数量。
     * 
     * @param minCalorie 热量最低值，包含。
     * @param maxCalorie 热量最高值，不包含。
     * @param goal 菜品推荐目标。
     */
    public int countByCalorieAndGoal(double minCalorie, int maxCalorie, int goal) {
        return cuisineDao.selectCountByCalorieAndGoal(minCalorie, maxCalorie, goal);
    }

    @Transactional(rollbackFor = Exception.class)
    public void add(CuisineVo cuisineVo) {
        cuisineDao.insert(cuisineVo);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateByCuisineCode(CuisineVo cuisineVo){
        cuisineDao.updateByCuisineCodeSelective(cuisineVo);
    }

    public List<CuisineVo> queryPage(IDPageParamVo paramVo) {
        return cuisineDao.selectFromIdWithLimit(paramVo.getFirstRowNumber(), paramVo.getRowNumber());
    }

    public List<CuisineVo> queryByStoreCode(String storeCode) {
        return cuisineDao.selectByStoreCode(storeCode);
    }

    public List<CuisineVo> queryByDineTime(int dineTime) {
        return cuisineDao.selectByDineTime(dineTime);
    }

    public CuisineVo queryByCuisineCode(String cuisineCode) {
        return cuisineDao.selectByCode(cuisineCode);
    }

    public List<CuisineVo> queryByCuisineCodeList(List<String> cuisineCodeList) {
        if (CollectionUtils.isEmpty(cuisineCodeList)) {
            return Collections.emptyList();
        }
        return cuisineDao.batchSelect(cuisineCodeList);
    }
}
