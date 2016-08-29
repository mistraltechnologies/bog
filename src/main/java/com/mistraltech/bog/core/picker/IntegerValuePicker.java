package com.mistraltech.bog.core.picker;

import java.util.Random;

public class IntegerValuePicker extends CachedValuePicker<Integer> implements ValuePicker<Integer> {
    private int minValue;
    private int maxValue;

    private IntegerValuePicker(int minValue, int maxValue) {
        // TODO check minValue <= maxValue
        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static IntegerValuePicker integerValuePicker(int minValue, int maxValue) {
        return new IntegerValuePicker(minValue, maxValue);
    }

    @Override
    protected Integer pickOnce() {
        // TODO check limits of range
        int range = maxValue - minValue;
        int n = new Random().nextInt(range);
        return minValue + n;
    }
}