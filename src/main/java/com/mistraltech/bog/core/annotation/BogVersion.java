package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Identifies the version of the Bog library in use. Reserved for use on {@link com.mistraltech.bog.core.Bog}.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface BogVersion {
    int major();

    int minor();
}
