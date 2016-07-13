package com.mistraltech.bog.core.picker;

import com.mifmif.common.regex.Generex;

public class RegexStringValuePicker extends CachedValuePicker<String> {
    private final String regex;

    protected RegexStringValuePicker(String regex) {
        this.regex = regex;
    }

    public static RegexStringValuePicker regexStringPicker(String regex) {
        return new RegexStringValuePicker(regex);
    }

    @Override
    protected String pickOnce() {
        final Generex generex = new Generex(regex);
        return generex.random();
    }
}
