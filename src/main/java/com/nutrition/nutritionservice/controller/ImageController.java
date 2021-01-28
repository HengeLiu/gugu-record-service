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
import javax.imageio.ImageIO;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
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
                configPropertiesService.getIngredientImagePath() + ingredientCode + ".webp");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/ingredient-icon", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getIngredientIcon(@RequestParam String ingredientCode) {
        byte[] ingredientBytes = getImageBytes(
                configPropertiesService.getIngredientIconPath() + ingredientCode + ".webp");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/shop-image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getShopImage(@RequestParam String shopCode) {
        byte[] ingredientBytes = getImageBytes(configPropertiesService.getShopImagePath() + shopCode + ".webp");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/shop-icon", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getShopIcon(@RequestParam String shopCode) {
        byte[] ingredientBytes = getImageBytes(configPropertiesService.getShopIconPath() + shopCode + ".webp");
        if (ingredientBytes.length == 0) {
            return getEmptyImageBytes();
        }
        return ingredientBytes;
    }

    @GetMapping(value = "/cuisine-image", produces = MediaType.IMAGE_JPEG_VALUE)
    @ResponseBody
    public byte[] getCuisineImage(@RequestParam String cuisineCode) {
        byte[] ingredientBytes = getImageBytes(configPropertiesService.getCuisineImagePath() + cuisineCode + ".webp");
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

    private byte[] getCompressImage(String imagePath) {
        File imageFile = new File(imagePath);
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        try {
            BufferedImage originBufferedImage = ImageIO.read(imageFile);
            int widthOrigin = originBufferedImage.getWidth();
            int heightOrigin = originBufferedImage.getHeight();
            int size = widthOrigin * heightOrigin;
            int maxPixel = 100;
            if (size <= maxPixel * maxPixel) {
                ImageIO.write(originBufferedImage, "jpeg", byteArrayOutputStream);
                return byteArrayOutputStream.toByteArray();
            }
            double scaleWidth = maxPixel / (double) (widthOrigin);
            double scaleHeight = maxPixel / (double) (heightOrigin);
            AffineTransform transform = new AffineTransform();
            transform.setToScale(scaleWidth, scaleHeight);
            // 生成转换操作对象
            AffineTransformOp transOp = new AffineTransformOp(transform, null);
            // 生成压缩图片缓冲对象
            BufferedImage newBufferedImage = new BufferedImage(maxPixel, maxPixel, BufferedImage.TYPE_3BYTE_BGR);
            // 生成缩小图片
            transOp.filter(originBufferedImage, newBufferedImage);
            ImageIO.write(newBufferedImage, "jpeg", byteArrayOutputStream);
            return byteArrayOutputStream.toByteArray();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return new byte[0];
    }

}
