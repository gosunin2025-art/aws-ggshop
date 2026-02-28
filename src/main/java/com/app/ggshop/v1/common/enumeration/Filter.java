package com.app.ggshop.v1.common.enumeration;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public enum Filter {

    individual("individual"), company("company");

    private String value;

    private static final Map<String, Filter> STATUS_MAP =
            Arrays.stream(Filter.values()).collect(Collectors.toMap(Filter::getValue, Function.identity()));

    Filter(String value) {
        this.value = value;
    }

    public static Filter getProvider(String value) {
        return STATUS_MAP.get(value);
    }

    public String getValue() {
        return value;
    }


}


