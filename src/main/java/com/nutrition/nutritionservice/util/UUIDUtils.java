package com.nutrition.nutritionservice.util;

import java.util.UUID;

/**
 * UUID工具。
 *
 * @author heng.liu
 * @since 2020/9/23
 */
public class UUIDUtils {

    public static String createRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
