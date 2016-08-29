package com.mistraltech.bog.core.picker;

import java.util.Random;

public final class BooleanValuePicker implements ValuePicker<Boolean> {

    private BooleanValuePicker() {
    }

    public static BooleanValuePicker booleanValuePicker() {
        return new BooleanValuePicker();
    }

    @Override
    public Boolean pick() {
        int n = new Random().nextInt(2);
        return n == 0;
    }
}
