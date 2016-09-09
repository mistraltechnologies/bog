package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies the property in the target object that the annotated method applies to.
 * <p>
 * Useful for overriding defaults assumed by the builder generators.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface BuildsProperty {
    /**
     * The namne of the property that is built
     *
     * @return the built property name
     */
    String value();
}
