package com.mistraltech.bog.core.picker;

import com.mifmif.common.regex.Generex;

import java.util.function.Supplier;

import static java.util.Objects.requireNonNull;

public class RegexStringRandomValuePicker implements Supplier<String> {
    private final Generex generex;

    protected RegexStringRandomValuePicker(String regex) {
        requireNonNull(regex);
        generex = new Generex(regex);
    }

    public static RegexStringRandomValuePicker regexStringRandomValuePicker(String regex) {
        return new RegexStringRandomValuePicker(regex);
    }

    @Override
    public String get() {
        return generex.random();
    }
}
