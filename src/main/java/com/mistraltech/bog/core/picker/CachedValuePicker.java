package com.mistraltech.bog.core.picker;

public abstract class CachedValuePicker<T> implements ValuePicker<T> {
    private T value;
    private boolean picked = false;

    @Override
    public final T pick() {
        if (!picked) {
            value = pickOnce();
            picked = true;
        }

        return value;
    }

    protected abstract T pickOnce();
}
