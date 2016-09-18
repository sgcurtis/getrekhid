package com.huskygames.rekhid.slugger.util;

public class NumberUtilities {
    public static int truncate(int value, int max) {
        if (value > max) {
            return max;
        }
        else {
            return value;
        }
    }

    public static double min(double a, double b) {
        return a > b ? b : a;
    }

    public static double max(double a, double b) {
        return a > b ? a : b;
    }
}
