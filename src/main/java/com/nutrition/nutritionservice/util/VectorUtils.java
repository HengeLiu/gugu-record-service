package com.nutrition.nutritionservice.util;

/**
 * 向量运算工具。
 *
 * @author heng.liu
 * @since 2020/12/20
 */
public class VectorUtils {

    public static long dotProduct(int[] a, int[] b) {
        checkLength(a, b);
        long c = 0;
        for (int i = 0; i < a.length; i++) {
            c += a[i] * b[i];
        }
        return c;
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

    public static int[] addition(int[] a, int[] b) {
        checkLength(a, b);
        int[] c = new int[a.length];
        for (int i = 0; i < a.length; i++) {
            c[i] = a[i] + b[i];
        }
        return c;
    }

    public static double cosineSimilarity(int[] a, int[] b) {
        return dotProduct(a, b) / (magnitude(a) * magnitude(b));
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

}
