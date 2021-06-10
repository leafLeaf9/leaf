package com.gousade.java8;

/**
 * @author woxigousade
 * @date 2021/6/7
 * https://blog.csdn.net/wokuailewozihao/article/details/94630904
 * https://lbsyun.baidu.com/jsdemo.htm#a6_1
 */
public class BaiduLocationUtils {
    public static Double getDistance(Point a, Point b) {
        if (a != null && b != null) {
            return XO(a, b);
        } else {
            return 0d;
        }
    }

    private static Double XO(Point a, Point b) {
        a.setLng(OD(a.getLng(), -180d, 180d));
        a.setLat(SD(a.getLat(), -74d, 74d));
        b.setLng(OD(b.getLng(), -180d, 180d));
        b.setLat(SD(b.getLat(), -74d, 74d));
        return Te(Uk(a.getLng()), Uk(b.getLng()), Uk(a.getLat()), Uk(b.getLat()));
    }

    private static Double OD(Double a, Double b, Double c) {
        while (a > c) {
            a -= c - b;
        }
        while (a < b) {
            a += c - b;
        }
        return a;
    }

    private static Double SD(Double a, Double b, Double c) {
        return a;
    }

    private static Double Te(Double a, Double b, Double c, Double d) {
        return 6370996.81 * Math.acos(Math.sin(c) * Math.sin(d) + Math.cos(c) * Math.cos(d) * Math.cos(b - a));
    }

    private static Double Uk(Double a) {
        return Math.PI * a / 180;
    }

    public static void main(String[] args) {

        System.out.println(getDistance(new Point(113.736347, 22.170973), new Point(113.78133725252464, 22.28640892913893)));
        System.out.println(getDistance(new Point(113.736347, 22.170973), new Point(113.651259, 22.250207)));
    }
}

class Point {
    /**
     * 经度
     */
    private Double lng;
    /**
     * 纬度
     */
    private Double lat;

    public Point() {
    }

    ;

    public Point(Double lng, Double lat) {
        this.lng = lng;
        this.lat = lat;
    }

    public Double getLng() {
        return lng;
    }

    public void setLng(Double lng) {
        this.lng = lng;
    }

    public Double getLat() {
        return lat;
    }

    public void setLat(Double lat) {
        this.lat = lat;
    }

    ;
}
