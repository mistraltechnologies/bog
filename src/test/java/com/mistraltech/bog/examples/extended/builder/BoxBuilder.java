package com.mistraltech.bog.examples.extended.builder;

import com.mistraltech.bog.examples.model.Box;
import com.mistraltech.bog.core.AbstractBuilder;
import com.mistraltech.bog.core.Builder;
import com.mistraltech.bog.core.PropertyBuilder;

public class BoxBuilder<P1, R extends BoxBuilder<P1, R, T>, T extends Box<P1>> extends AbstractBuilder<T> {
    protected PropertyBuilder<P1> contents = new PropertyBuilder<P1>();

    protected BoxBuilder() {
    }

    protected BoxBuilder(T template) {
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
        if (contents.hasValue()) {
            instance.setContents(contents.getOrDefault(null));
        }
    }

    public static final class BoxBuilderType<T> extends BoxBuilder<T, BoxBuilderType<T>, Box<T>> {
        public BoxBuilderType() {
        }

        public BoxBuilderType(Box<T> template) {
            super(template);
        }

        public static <T> BoxBuilderType<T> aBox() {
            return new BoxBuilderType<T>();
        }

        public static <T> BoxBuilderType<T> aBoxFrom(Box<T> template) {
            return new BoxBuilderType<T>(template);
        }

        protected Box<T> construct() {
            return new Box<T>();
        }
    }
}
