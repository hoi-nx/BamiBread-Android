package com.t3h.bamibread.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by Heart Of Dead on 9/16/2017.
 */

public class Directions {
    @SerializedName("routes")
    private List<Route> routes;

    public List<Route> getRoutes() {
        return routes;
    }

    public class Route {

        @SerializedName("legs")
        private List<Leg> leg;

        public List<Leg> getLeg() {
            return leg;
        }
    }

    public class Leg {
        @SerializedName("steps")
        private List<Step> steps;

        public List<Step> getSteps() {
            return steps;
        }
    }

    public class Step {
        @SerializedName("distance")
        private Distance distance;

        @SerializedName("end_location")
        private Location endLocation;

        @SerializedName("start_location")
        private Location startLocation;

        @SerializedName("polyline")
        private Polyline polyline;

        public Polyline getPolyline() {
            return polyline;
        }

        public Distance getDistance() {
            return distance;
        }

        public Location getEndLocation() {
            return endLocation;
        }

        public Location getStartLocation() {
            return startLocation;
        }
    }

    private static class Location {
        @SerializedName("lat")
        private double lat;

        @SerializedName("lng")
        private double lng;

        public double getLat() {
            return lat;
        }

        public double getLng() {
            return lng;
        }
    }

    private static class Distance {
        @SerializedName("text")
        private String text;

        @SerializedName("value")
        private String value;
    }

    public class Polyline {
        @SerializedName("points")
        private String points;

        public String getPoints() {
            return points;
        }
    }
}
