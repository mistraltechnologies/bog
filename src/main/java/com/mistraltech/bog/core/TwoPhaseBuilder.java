package com.mistraltech.bog.core;

public interface TwoPhaseBuilder<T> extends Builder<T> {

    T create();

    T update();
}
