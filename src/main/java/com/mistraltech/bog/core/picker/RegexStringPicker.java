package com.mistraltech.bog.core.picker;

import com.mifmif.common.regex.Generex;

public class RegexStringPicker implements Picker<String> {
    private final String regex;


    protected RegexStringPicker(String regex) {
        this.regex = regex;
    }

    public static RegexStringPicker regexStringPicker(String regex) {
        return new RegexStringPicker(regex);
    }

    @Override
    public String pick() {
        final Generex generex = new Generex(regex);
        return generex.random();
    }
}
