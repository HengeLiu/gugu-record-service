package com.nutrition.nutritionservice;

import lombok.extern.slf4j.Slf4j;
import org.gavaghan.geodesy.Ellipsoid;
import org.gavaghan.geodesy.GeodeticCalculator;
import org.gavaghan.geodesy.GlobalCoordinates;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.awt.geom.Point2D;

/**
 * 地理工具测试
 *
 * @author heng.liu
 * @since 2020/12/30
 */
@SpringBootTest
@Slf4j
public class GeoTest {

    private int EARTH_R = 0;

    @Test
    public void test() {
        GeodeticCalculator geodeticCalculator = new GeodeticCalculator();
        GlobalCoordinates source = new GlobalCoordinates(116.444424, 39.939794);
        GlobalCoordinates destination = geodeticCalculator.calculateEndingGlobalCoordinates(Ellipsoid.Sphere, source, 0,
                0);

        log.info("\n{},{}", destination.getLongitude(), destination.getLatitude());
    }

    @Test
    public void testDistance() {
        Point2D point1 = new Point2D.Double(116.444424, 39.939794);
        Point2D point2 = new Point2D.Double(116.444424, 39.939794);

    }

    /**
     * 将角度转化为弧度
     */
    public static double radians(double d) {
        return d * Math.PI / 180.0;
    }

    /**
     * 根据两点经纬度坐标计算直线距离
     * <p>
     * S = 2arcsin√sin²(a/2)+cos(lat1)*cos(lat2)*sin²(b/2)￣*6378.137
     * <p>
     * 1. lng1 lat1 表示A点经纬度，lng2 lat2 表示B点经纬度；<br>
     * 2. a=lat1 – lat2 为两点纬度之差 b=lng1 -lng2 为两点经度之差；<br>
     * 3. 6378.137为地球赤道半径，单位为千米；
     *
     * @param lng1 点1经度
     * @param lat1 点1纬度
     * @param lng2 点2经度
     * @param lat2 点2纬度
     * @return 距离，单位千米(KM)
     * @see <a href="https://zh.wikipedia.org/wiki/%E5%8D%8A%E6%AD%A3%E7%9F%A2%E5%85%AC%E5%BC%8F">半正矢(Haversine)公式</a>
     */
    public static double getDistanceFrom2LngLat(double lng1, double lat1, double lng2, double lat2) {
        // 将角度转化为弧度
        double radLng1 = radians(lng1);
        double radLat1 = radians(lat1);
        double radLng2 = radians(lng2);
        double radLat2 = radians(lat2);

        double a = radLat1 - radLat2;
        double b = radLng1 - radLng2;

        return 2 * Math.asin(Math.sqrt(Math.sin(a / 2) * Math.sin(a / 2)
                + Math.cos(radLat1) * Math.cos(radLat2) * Math.sin(b / 2) * Math.sin(b / 2))) * 6378.137;
    }

}
