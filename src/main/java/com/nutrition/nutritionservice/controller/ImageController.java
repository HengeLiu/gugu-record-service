package com.nutrition.nutritionservice.controller;

import com.nutrition.nutritionservice.service.ConfigPropertiesService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

/**
 * 图片链接
 *
 * @author heng.liu
 * @since 2021/1/17
 */
@Slf4j
@Controller
@RequestMapping("/static/")
public class ImageController {

    @Resource
    private ConfigPropertiesService configPropertiesService;

    @GetMapping(value = "/ingredient-image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getIngredientImage(@RequestParam String ingredientCode) {
        byte[] ingredientBytes = getImageBytes(
                configPropertiesService.getIngredientImagePath() + ingredientCode + ".jpeg");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/ingredient-icon", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getIngredientIcon(@RequestParam String ingredientCode) {
        byte[] ingredientBytes = getImageBytes(
                configPropertiesService.getIngredientIconPath() + ingredientCode + ".jpeg");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/shop-image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getShopImage(@RequestParam String shopCode) {
        byte[] ingredientBytes = getImageBytes(configPropertiesService.getShopImagePath() + shopCode + ".jpeg");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/shop-icon", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getShopIcon(@RequestParam String shopCode) {
        byte[] ingredientBytes = getImageBytes(configPropertiesService.getShopIconPath() + shopCode + ".jpeg");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/cuisine-image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getCuisineImage(@RequestParam String cuisineCode) {
        byte[] ingredientBytes = getImageBytes(configPropertiesService.getCuisineImagePath() + cuisineCode + ".jpeg");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    private byte[] getEmptyImageBytes() {
        return getImageBytes(configPropertiesService.getEmptyImagePath());
    }

    private byte[] getImageBytes(String imagePath) {
        InputStream inputStream;
        try {
            File file = new File(imagePath);
            inputStream = new FileInputStream(file);
        } catch (FileNotFoundException e) {
            log.error("Image not found, {}.", imagePath);
            return new byte[0];
        }
        byte[] bytes = new byte[0];
        try {
            bytes = new byte[inputStream.available()];
            int byteLength = inputStream.read(bytes, 0, inputStream.available());
            if (byteLength > 1024 * 1024 * 1024) {
                log.warn("Image size too large, {}", imagePath);
            }
        } catch (IOException e) {
            log.error("IOException when getting image {}", imagePath, e);
        }
        return bytes;
    }

}
