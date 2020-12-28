package com.nutrition.nutritionservice.util;

import com.nutrition.nutritionservice.vo.modeldata.IntakesModelVo;

/**
 * 模型工具
 *
 * @author heng.liu
 * @since 2020/12/28
 */
public class ModelUtil {

    public static int[] modelToVector(IntakesModelVo model) {
        int[] v = new int[16];
        v[0] = model.getProcessedGrains();
        v[1] = model.getUnprocessedGrains();
        v[2] = model.getMixedBeans();
        v[3] = model.getTuber();
        v[4] = model.getGeneralVegetables();
        v[5] = model.getDarkVegetables();
        v[6] = model.getFruit();
        v[7] = model.getMeat();
        v[8] = model.getPoultry();
        v[9] = model.getAquatic();
        v[10] = model.getEgg();
        v[11] = model.getDairy();
        v[12] = model.getSoybean();
        v[13] = model.getNut();
        v[14] = model.getOil();
        v[15] = model.getSalt();
        return v;
    }

    public static double calculateCosineSimilarity(IntakesModelVo model1, IntakesModelVo model2) {
        int[] v1 = ModelUtil.modelToVector(model1);
        int[] v2 = ModelUtil.modelToVector(model2);
        return VectorUtils.cosineSimilarity(v1, v2);
    }

    public static double calculateEuclidDistance(IntakesModelVo model1, IntakesModelVo model2) {
        int[] v1 = ModelUtil.modelToVector(model1);
        int[] v2 = ModelUtil.modelToVector(model2);
        return VectorUtils.euclidDistance(v1, v2);
    }

}
