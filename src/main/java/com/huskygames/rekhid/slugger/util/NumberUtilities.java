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
}
