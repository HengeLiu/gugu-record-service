package com.nutrition.nutritionservice.enums;

import java.io.Serializable;

/**
 * 带有Code属性的枚举接口。
 * 
 * @author heng.liu
 * @since 2020/9/21
 */
public interface CodeEnum<T> extends Serializable {

    T getCode();

}
