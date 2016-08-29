package com.mistraltech.bog.core.picker;

import com.mifmif.common.regex.Generex;

import static java.util.Objects.requireNonNull;

public class RegexStringValuePicker implements ValuePicker<String> {
    private final Generex generex;

    protected RegexStringValuePicker(String regex) {
        requireNonNull(regex);
        generex = new Generex(regex);
    }

    public static RegexStringValuePicker regexStringValuePicker(String regex) {
        return new RegexStringValuePicker(regex);
    }

    @Override
    public String pick() {
        return generex.random();
    }
}
