package com.gousade.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author woxigsd@gmail.com
 * @date 2020年7月31日 上午9:36:12
 */
public class BigDecimalCalculator {

    public static float add(float[] array) {
        BigDecimal b1 = new BigDecimal(Float.toString(0f));
        BigDecimal b2 = null;
        for (float f : array) {
            b2 = new BigDecimal(Float.toString(f));
            b1 = b1.add(b2);
        }
        return b1.floatValue();

    }

    public static float add(Float[] array) {
        BigDecimal b1 = new BigDecimal(Float.toString(0f));
        BigDecimal b2 = null;
        for (Float f : array) {
            b2 = new BigDecimal(Float.toString(f));
            b1 = b1.add(b2);
        }
        return b1.floatValue();

    }

    public static float subtract(float b, float[] array) {
        BigDecimal b1 = new BigDecimal(Float.toString(b));
        BigDecimal b2 = null;
        for (float f : array) {
            b2 = new BigDecimal(Float.toString(f));
            b1 = b1.subtract(b2);
        }
        return b1.floatValue();

    }

    public static float multiply(int b, float f) {
        BigDecimal b1 = new BigDecimal(Float.toString(b));
        BigDecimal b2 = new BigDecimal(Float.toString(f));

        b1 = b1.multiply(b2);
        return b1.floatValue();
    }

    public static double multiply(double a, double b) {
        BigDecimal a1 = new BigDecimal(Double.toString(a));
        BigDecimal b1 = new BigDecimal(Double.toString(b));
        a1 = a1.multiply(b1);
        return a1.doubleValue();
    }

    /**
     * @param a
     * @param b
     * @return 四舍五入 保留2位
     */
    public static double divide(double a, double b) {
        BigDecimal a1 = new BigDecimal(Double.toString(a));
        BigDecimal b1 = new BigDecimal(Double.toString(b));
        a1 = a1.divide(b1, 2, RoundingMode.HALF_UP);
        return a1.doubleValue();
    }

    public static String generateId() {
        Date date = new Date();
        SimpleDateFormat dateformat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        String id = dateformat.format(date);
        for (int i = 0; i < 4; i++) {
            int num = (int) (Math.random() * 10);
            id = id + num;
        }
        return id;
    }
}
