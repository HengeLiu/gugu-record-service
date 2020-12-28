package com.nutrition.nutritionservice.util;

import java.awt.image.CropImageFilter;
import java.util.Vector;

/**
 * 向量运算工具。
 *
 * @author heng.liu
 * @since 2020/12/20
 */
public class VectorUtil {

    public static long dotProduct(int[] a, int[] b) {
        checkLength(a, b);
        long c = 0;
        for (int i = 0; i < a.length; i++) {
            c += a[i] * b[i];
        }
        return c;
    }

    public static double dotProduct(Vector<? extends Number> v1, Vector<? extends Number> v2) {
        checkLength(v1, v2);
        double c = 0;
        for (int i = 0; i < v1.size(); i++) {
            c += v1.get(i).doubleValue() * v2.get(i).doubleValue();
        }
        return c;
    }

    public static Vector<Double> crossProduct(Vector<? extends Number> v1, Vector<? extends Number> v2) {
        checkLength(v1, v2);
        Vector<Double> crossProduct = new Vector<>();
        for (int i = 0; i < v1.size(); i++) {
            crossProduct.add(i, v1.get(i).doubleValue() * v2.get(i).doubleValue());
        }
        return crossProduct;
    }

    public static double magnitude(Vector<? extends Number> v) {
        long c = 0;
        for (Number j : v) {
            c += Math.pow(j.doubleValue(), 2);
        }
        return Math.pow(c, 0.5);
    }

    public static double magnitude(int[] a) {
        long c = 0;
        for (int j : a) {
            c += Math.pow(j, 2);
        }
        return Math.pow(c, 0.5);
    }

    public static int[] subtraction(int[] a, int[] b) {
        checkLength(a, b);
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] - b[i];
        }
        return c;
    }

    public static Vector<Double> subtraction(Vector<? extends Number> v1, Vector<? extends Number> v2) {
        checkLength(v1, v2);
        Vector<Double> crossProduct = new Vector<>();
        for (int i = 0; i < v1.size(); i++) {
            crossProduct.add(i, v1.get(i).doubleValue() - v2.get(i).doubleValue());
        }
        return crossProduct;
    }

    public static int[] addition(int[] a, int[] b) {
        checkLength(a, b);
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static double cosineSimilarity(Vector<? extends Number> v1, Vector<? extends Number> v2) {
        return dotProduct(v1, v2) / (magnitude(v1) * magnitude(v2));
    }

    public static double cosineSimilarity(int[] a, int[] b) {
        return dotProduct(a, b) / (magnitude(a) * magnitude(b));
    }

    public static double euclidDistance(Vector<? extends Number> v1, Vector<? extends Number> v2) {
        checkLength(v1, v2);
        long squareDistance = 0;
        for (int i = 0; i < v1.size(); i++) {
            squareDistance += Math.pow(v1.get(i).doubleValue() - v2.get(i).doubleValue(), 2);
        }
        return Math.pow(squareDistance, 0.5);
    }

    public static double euclidDistance(int[] a, int[] b) {
        checkLength(a, b);
        long squareDistance = 0;
        for (int i = 0; i < a.length; i++) {
            squareDistance += Math.pow(a[i] - b[i], 2);
        }
        return Math.pow(squareDistance, 0.5);
    }

    private static void checkLength(int[] a, int[] b) {
        if (a.length != b.length) {
            throw new IllegalArgumentException("Vector a and vector b should have equals length.");
        }
    }

    private static void checkLength(Vector<? extends Number> v1, Vector<? extends Number> v2) {
        if (v1.size() != v2.size()) {
            throw new IllegalArgumentException("Vector 1 and vector 2 should have equals length.");
        }
    }

}
