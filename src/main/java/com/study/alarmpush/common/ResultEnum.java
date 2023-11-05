package com.study.alarmpush.common;


public enum ResultEnum {
    SUCCESS("SUCCESS"),
    FAIL("FAIL"),
    EMPTY_RESULT("EMPTY DATA");

    private final String resultString;

    private ResultEnum(String resultString) {
        this.resultString = resultString;
    }

    public String getResultString() {
        return this.resultString;
    }
}
