package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Indicates that a builder method relates to a property that is set on the target instance
 * through a constructor parameter.
 * <p>
 * The annotation declares the position in the constructor parameter
 * list where the property value should appear.
 */
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
