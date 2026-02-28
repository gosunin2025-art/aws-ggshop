package com.app.ggshop.v1.common.enumeration;

public enum BoardFilter {
    ALL("all"),
    BUY("구매"),      //  한글로 변경!
    SELL("판매");     //   한글로 변경!

    private final String value;

    BoardFilter(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}