package com.kh.rupp_dev.boukryuniversity.validation;

public class DistanceValidation {

    public static double haversine(double latitude, double longtitude1, double latitube2, double longtitude2) {

        final int radiuse = 6371000;
        double distaneLatitude = Math.toRadians(latitube2 - latitude);
        double distaneLongtitude = Math.toRadians(longtitude2 - longtitude1);
        double area = Math.sin(distaneLatitude/2)*Math.sin(distaneLatitude/2) + Math.cos(Math.toRadians(latitude))*Math.cos(Math.toRadians(latitube2))*Math.sin(distaneLongtitude/2)*Math.sin(distaneLongtitude/2);
        double circle = 2 *Math.atan2(Math.sqrt(area), Math.sqrt(1 - area));

        return radiuse * circle;
    }
}
