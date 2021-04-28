package com.nutrition.nutritionservice;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

/**
 * @author heng.liu
 * @since 2020/9/23
 */
@SpringBootTest
public class UUIDTests {

    @Test
    public void testCreateUUID() {

        UUID uuid = UUID.randomUUID();
        System.out.println(uuid);

    }

}
