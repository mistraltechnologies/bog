package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

@Target(METHOD)
@Retention(RUNTIME)
public @interface ConstructorParameter {
    /**
     * The position of the property in the constructor parameter list (zero-based).
     *
     * @return the parameter index
     */
    int value();
}
