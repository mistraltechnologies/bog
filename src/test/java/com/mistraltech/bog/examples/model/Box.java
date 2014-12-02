package com.mistraltech.bog.examples.model;

import java.beans.ConstructorProperties;

public class Box<T> {
    private T contents;

    @ConstructorProperties({"contents"})
    public Box() {
    }

    public void setContents(T contents) {
        this.contents = contents;
    }

    public T getContents() {
        return contents;
    }
}
