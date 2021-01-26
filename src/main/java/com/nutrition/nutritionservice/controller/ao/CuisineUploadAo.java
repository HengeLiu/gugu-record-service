package com.nutrition.nutritionservice.controller.ao;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 餐品上传
 *
 * @author heng.liu
 * @since 2021/1/20
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CuisineUploadAo implements Serializable {
    private static final long serialVersionUID = -3133858795574696190L;

    private String storeCode;

    private String cuisineName;

    private List<String> baseCuisineCodeList;

    private List<CuisineIngredientAo> ingredientWeightList;

}
