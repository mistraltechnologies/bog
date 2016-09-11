package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies the property in the builder that the annotated method provides default values for.
 * <p>
 * Useful for overriding defaults assumed by the builder generators.
 */
@Target(METHOD)
@Retention(RUNTIME)
public @interface GetsPropertyDefault {
    /**
     * The name of the property to generate a default for
     *
     * @return the name of the property
     */
    String value();
}
