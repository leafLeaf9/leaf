package com.gousade.utils;

import java.math.BigDecimal;
import java.math.RoundingMode;

/**
 * @author woxigsd@gmail.com
 * @date 2020年7月31日 上午9:36:12
 */
public class BigDecimalCalculator {

    public static double add(double... array) {
        BigDecimal b1 = new BigDecimal(Double.toString(0));
        BigDecimal b2;
        for (double augend : array) {
            b2 = BigDecimal.valueOf(augend);
            b1 = b1.add(b2);
        }
        return b1.doubleValue();

    }

    public static double subtract(double b, double... array) {
        BigDecimal b1 = BigDecimal.valueOf(b);
        BigDecimal b2;
        for (double subtrahend : array) {
            b2 = BigDecimal.valueOf(subtrahend);
            b1 = b1.subtract(b2);
        }
        return b1.doubleValue();
    }

    public static double multiply(int a, double b) {
        BigDecimal b1 = BigDecimal.valueOf(a);
        BigDecimal b2 = BigDecimal.valueOf(b);
        b1 = b1.multiply(b2);
        return b1.doubleValue();
    }

    public static double multiply(double a, double b) {
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b1 = BigDecimal.valueOf(b);
        a1 = a1.multiply(b1);
        return a1.doubleValue();
    }

    public static double divide(double a, double b, int scale) {
        BigDecimal a1 = BigDecimal.valueOf(a);
        BigDecimal b1 = BigDecimal.valueOf(b);
        a1 = a1.divide(b1, scale, RoundingMode.HALF_UP);
        return a1.doubleValue();
    }

    public static String getChanges(double a, double b) {
        double value = BigDecimal.valueOf(a).subtract(BigDecimal.valueOf(b))
                .divide(BigDecimal.valueOf(b), 4, RoundingMode.HALF_UP)
                .doubleValue();
        if (value > 0.0D) {
            return "上升" + BigDecimal.valueOf(value).multiply(BigDecimal.valueOf(100)).doubleValue() + "%";
        } else if (value < 0.0D) {
            return "下降" + BigDecimal.valueOf(Math.abs(value)).multiply(BigDecimal.valueOf(100)).doubleValue() + "%";
        } else {
            return "持平";
        }
    }

    public static double getDistance(double startPoint, double endPoint, int scale) {
        return Math.abs(BigDecimal.valueOf(startPoint).subtract(BigDecimal.valueOf(endPoint))
                .setScale(scale, RoundingMode.HALF_UP).doubleValue());
    }

}
