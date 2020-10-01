package com.nutrition.nutritionservice.vo.user;

import com.nutrition.nutritionservice.vo.TimeBasedVo;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户摄入类型模型。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class UserIntakeCategoryModelVo extends TimeBasedVo {

    private long id;

    private String uuid;

    private int subCategoryCode;

    private int perDayWeight;
}
