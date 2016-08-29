package com.mistraltech.bog.core.picker;

import com.mifmif.common.regex.Generex;

public class RegexStringValuePicker implements ValuePicker<String> {
    private final String regex;

    protected RegexStringValuePicker(String regex) {
        this.regex = regex;
    }

    public static RegexStringValuePicker regexStringValuePicker(String regex) {
        return new RegexStringValuePicker(regex);
    }

    @Override
    public String pick() {
        final Generex generex = new Generex(regex);
        return generex.random();
    }
}
