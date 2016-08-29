package com.mistraltech.bog.core.picker;

import java.util.Random;

public class IntegerValuePicker implements ValuePicker<Integer> {
    private int minValue;
    private int maxValue;

    private IntegerValuePicker(int minValue, int maxValue) {
        if (maxValue < minValue) {
            throw new IllegalArgumentException("Invalid range");
        }

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static IntegerValuePicker integerValuePicker(int minValue, int maxValue) {
        return new IntegerValuePicker(minValue, maxValue);
    }

    @Override
    public Integer pick() {
        int range = maxValue - minValue + 1;
        int n = new Random().nextInt(range);
        return minValue + n;
    }
}
