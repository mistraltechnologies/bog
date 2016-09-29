package com.mistraltech.bog.core.picker;

import java.util.Random;
import java.util.function.Supplier;

public class IntegerRandomValuePicker implements Supplier<Integer> {
    private int minValue;
    private int maxValue;
    private Random random = new Random();

    private IntegerRandomValuePicker(int minValue, int maxValue) {
        if (maxValue < minValue) {
            throw new IllegalArgumentException("Invalid range");
        }

        this.minValue = minValue;
        this.maxValue = maxValue;
    }

    public static IntegerRandomValuePicker integerValuePicker(int minValue, int maxValue) {
        return new IntegerRandomValuePicker(minValue, maxValue);
    }

    @Override
    public Integer get() {
        int range = maxValue - minValue + 1;
        int n = random.nextInt(range);
        return minValue + n;
    }
}
