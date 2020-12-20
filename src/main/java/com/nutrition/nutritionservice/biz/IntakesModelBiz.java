package com.nutrition.nutritionservice.biz;

import com.google.common.collect.Lists;
import com.nutrition.nutritionservice.annotation.Biz;
import com.nutrition.nutritionservice.service.EnergyCalorieCalculateService;
import com.nutrition.nutritionservice.service.ModelIngredientIntakesService;
import com.nutrition.nutritionservice.vo.IngredientCategoryIntakesVo;
import com.nutrition.nutritionservice.vo.IngredientSubCategoryIntakesVo;
import com.nutrition.nutritionservice.vo.IntakesModelVo;
import com.nutrition.nutritionservice.vo.ModelParamVo;
import com.nutrition.nutritionservice.vo.modeldata.ModelIngredientIntakesVo;
import com.nutrition.nutritionservice.vo.user.UserIntakeCategoryModelVo;
import lombok.extern.slf4j.Slf4j;

import javax.annotation.Resource;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 摄入模型业务层。
 * 
 * @author heng.liu
 * @since 2020/9/13
 */
@Biz
@Slf4j
public class IntakesModelBiz {

    @Resource
    private EnergyCalorieCalculateService energyCalorieCalculateService;

    @Resource
    private ModelIngredientIntakesService modelIngredientIntakesService;

    public ModelIngredientIntakesVo calculateIntakesModel(ModelParamVo paramVo) {
        int dailyEnergy = energyCalorieCalculateService.calculate(paramVo);
        return modelIngredientIntakesService.getIntakesByCalorieGoal(dailyEnergy, paramVo.getGoal());
    }

    // public IntakesModelVo calculateIntakesModel(ModelParamVo param) {
    // ModelMetabolismLevelVo modelMetabolismLevelVo = metabolismLevelService.selectByGenderAndAge(param.getGender(),
    // param.getAge());
    // Map<String, Double> sportLevelValueMap = configPropertiesService.sportLevelValueMap();
    // double dailyCalorie = modelMetabolismLevelVo.getMetabolismLevel() * param.getWeight()
    // * sportLevelValueMap.get(String.valueOf(param.getSportLevel()));
    // double modelCalorieValue = ingredientCategoryIntakesService.queryMaxCalorieLte(dailyCalorie);
    // List<ModelCalorieIngredientSubCategoryIntakesVo> subCategoryIntakesModelList = ingredientCategoryIntakesService
    // .queryByCalorie(modelCalorieValue);
    // Map<IngredientSubCategoryEnum, ModelCalorieIngredientSubCategoryIntakesVo> subCategoryGroupMap =
    // subCategoryIntakesModelList
    // .stream().collect(Collectors.toMap(subIngredient -> CodeEnums.valueOf(IngredientSubCategoryEnum.class,
    // subIngredient.getIngredientSubCategoryCode()), Function.identity()));
    // List<IngredientCategoryIntakesVo> categoryIntakesList = Lists.newArrayList();
    // subCategoryGroupMap.entrySet().stream()
    // // 按照大分类排序
    // .collect(Collectors.groupingBy(subCategoryEntry -> subCategoryEntry.getKey().getParentCategory()))
    // .forEach((categoryEnum, subCategoryEntryList) -> {
    // List<IngredientSubCategoryIntakesVo> subCategoryIntakesVoList = Lists.newArrayList();
    // for (Map.Entry<IngredientSubCategoryEnum, ModelCalorieIngredientSubCategoryIntakesVo> subCategoryEntry :
    // subCategoryEntryList) {
    // IngredientSubCategoryEnum subCategoryEnum = subCategoryEntry.getKey();
    // subCategoryIntakesVoList
    // .add(IngredientSubCategoryIntakesVo.builder().subCategoryCode(subCategoryEnum.getCode())
    // .weight((int) subCategoryEntry.getValue().getDailyWeight())
    // .zhName(categoryEnum.getDesc()).build());
    // }
    // categoryIntakesList.add(IngredientCategoryIntakesVo.builder().categoryCode(categoryEnum.getCode())
    // .zhName(categoryEnum.getDesc()).subCategoryIntakesList(subCategoryIntakesVoList)
    // .weight(subCategoryIntakesVoList.stream()
    // .mapToInt(IngredientSubCategoryIntakesVo::getWeight).sum())
    // .build());
    // });
    // categoryIntakesList.sort(Comparator.comparing(IngredientCategoryIntakesVo::getCategoryCode));
    // return IntakesModelVo.builder().categoryIntakesList(categoryIntakesList).build();
    // }

    public boolean save(IntakesModelVo intakesModel) {
        String uuid = intakesModel.getUuid();
        List<IngredientSubCategoryIntakesVo> subCategoryIntakesList = intakesModel.getCategoryIntakesList().stream()
                .map(IngredientCategoryIntakesVo::getSubCategoryIntakesList).flatMap(Collection::stream)
                .collect(Collectors.toList());
        List<UserIntakeCategoryModelVo> userIntakeCategoryModelVoList = Lists.newArrayList();
        for (IngredientSubCategoryIntakesVo subCategoryIntakesVo : subCategoryIntakesList) {
            UserIntakeCategoryModelVo userIntakeCategoryModelVo = new UserIntakeCategoryModelVo();
            userIntakeCategoryModelVo.setUuid(uuid);
            userIntakeCategoryModelVo.setSubCategoryCode(subCategoryIntakesVo.getSubCategoryCode());
            userIntakeCategoryModelVo.setPerDayWeight(subCategoryIntakesVo.getWeight());
            userIntakeCategoryModelVoList.add(userIntakeCategoryModelVo);
        }

        return true;
    }

    public IntakesModelVo query(String uuid) {

        return null;
    }

}
