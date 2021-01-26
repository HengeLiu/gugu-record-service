package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.IngredientService;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.nutrition.nutritionservice.vo.store.CuisineIngredientRelVo;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

import javax.annotation.Resource;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * 食材业务。
 * 
 * @author heng.liu
 * @since 2020/12/26
 */
@Biz
@Slf4j
public class IngredientBiz {

    @Resource
    private IngredientService ingredientService;

    public double calculateCalorie(List<CuisineIngredientRelVo> cuisineIngredientRelList) {
        Map<Integer, Integer> ingredientWeightMap = cuisineIngredientRelList.stream().collect(
                Collectors.toMap(CuisineIngredientRelVo::getIngredientCode, CuisineIngredientRelVo::getWeight));
        List<IngredientVo> ingredientVoList = ingredientService
                .queryByCodeList(Lists.newArrayList(ingredientWeightMap.keySet()));
        double cuisineCalorie = 0;
        for (IngredientVo ingredient : ingredientVoList) {
            cuisineCalorie += (ingredientWeightMap.getOrDefault(ingredient.getCode(), 0) / 100.0)
                    * ingredient.getCalorie();
        }
        return cuisineCalorie;
    }

    public List<IngredientVo> queryAvailable() {
        HanyuPinyinOutputFormat hanyuPinyinOutputFormat = new HanyuPinyinOutputFormat();
        hanyuPinyinOutputFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
        return ingredientService.queryAvailable().stream().sorted(Comparator.comparing(ingredientVo -> {
            try {
                return PinyinHelper.toHanYuPinyinString(ingredientVo.getName(), hanyuPinyinOutputFormat, "", false);
            } catch (BadHanyuPinyinOutputFormatCombination badHanyuPinyinOutputFormatCombination) {
                log.error("Convert ingredient Chinese name to Pinyin failed, {}.", ingredientVo.getName());
            }
            return ingredientVo.getName();
        })).collect(Collectors.toList());
    }

}
