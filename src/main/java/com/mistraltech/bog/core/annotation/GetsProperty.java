package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies the property in the builder that the annotated method is a getter for.
 * <p>
 * Useful for overriding defaults assumed by the builder generators.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface GetsProperty {
    /**
     * The name of the property to be returned
     *
     * @return the ValueContainer for the property
     */
    String value();
}
