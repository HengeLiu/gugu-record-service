package com.nutrition.nutritionservice.converter;

import com.nutrition.nutritionservice.controller.ao.IngredientAo;
import com.nutrition.nutritionservice.vo.IngredientVo;
import com.sun.tools.javac.util.StringUtils;
import lombok.extern.slf4j.Slf4j;
import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author heng.liu
 * @since 2021/1/26
 */
@Slf4j
public class IngredientVo2AoConverter {

    public static List<IngredientAo> convertToList(List<IngredientVo> ingredientVoList,
            HanyuPinyinOutputFormat pinyinOutputFormat) {
        if (CollectionUtils.isEmpty(ingredientVoList)) {
            return Collections.emptyList();
        }
        return ingredientVoList.stream().map(ingredientVo -> convert(ingredientVo, pinyinOutputFormat))
                .collect(Collectors.toList());
    }

    public static IngredientAo convert(IngredientVo ingredientVo, HanyuPinyinOutputFormat pinyinOutputFormat) {
        IngredientAo ingredientAo = new IngredientAo();
        ingredientAo.setCode(ingredientVo.getCode());
        ingredientAo.setName(ingredientVo.getName());
        try {
            ingredientAo.setFirstPinyin(StringUtils
                    .toUpperCase(
                            PinyinHelper.toHanYuPinyinString(ingredientVo.getName(), pinyinOutputFormat, "", false))
                    .charAt(0));
        } catch (Exception e) {
            log.error("Convert ingredient Chinese name to Pinyin failed, {}.", ingredientVo.getName());
        }
        ingredientAo.setCalorie(ingredientVo.getCalorie());
        ingredientAo.setCategoryCode(ingredientVo.getCategoryCode());
        ingredientAo.setCategoryName(ingredientVo.getCategoryName());
        ingredientAo.setNicknames(ingredientVo.getNicknames());
        ingredientAo.setSpecifications(ingredientVo.getSpecifications());
        ingredientAo.setIngredientType(ingredientVo.getIngredientType());
        return ingredientAo;
    }

}
