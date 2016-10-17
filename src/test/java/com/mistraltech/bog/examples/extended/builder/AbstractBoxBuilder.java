package com.mistraltech.bog.examples.extended.builder;

import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.ValueContainer;
import com.mistraltech.bog.examples.model.Box;

public abstract class AbstractBoxBuilder<P1, R extends AbstractBoxBuilder<P1, R, T>, T extends Box<P1>> extends AbstractBuilder<T> {
    protected ValueContainer<P1> contents = new ValueContainer<>();

    protected AbstractBoxBuilder() {
    }

    protected AbstractBoxBuilder(T template) {
        withContents(template.getContents());
    }

    @SuppressWarnings("unchecked")
    protected R self() {
        return (R) this;
    }

    public R withContents(P1 contents) {
        this.contents.set(contents);
        return self();
    }

    public R withContents(Builder<? extends P1> contents) {
        this.contents.set(contents);
        return self();
    }

    @Override
    protected void assign(T instance) {
        if (contents.isAssigned()) {
            instance.setContents(contents.value());
        }
    }

    public static final class BoxBuilder<T> extends AbstractBoxBuilder<T, BoxBuilder<T>, Box<T>> {
        public BoxBuilder() {
        }

        public BoxBuilder(Box<T> template) {
            super(template);
        }

        public static <T> BoxBuilder<T> aBox() {
            return new BoxBuilder<>();
        }

        public static <T> BoxBuilder<T> aBoxFrom(Box<T> template) {
            return new BoxBuilder<>(template);
        }

        protected Box<T> construct() {
            return new Box<>();
        }
    }
}
