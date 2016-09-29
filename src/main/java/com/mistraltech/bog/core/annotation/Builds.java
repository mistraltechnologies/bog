package com.mistraltech.bog.core.annotation;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import static java.lang.annotation.ElementType.TYPE;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

/**
 * Builder class annotation that identifies the class that builder builds.
 */
@Target(TYPE)
@Retention(RUNTIME)
public @interface Builds {
    Class<?> value();
}
