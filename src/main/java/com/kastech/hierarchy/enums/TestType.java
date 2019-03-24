package com.kastech.hierarchy.enums;

public enum TestType {
    Manual("manual"),
    Automation("automation");

    String testType;

    TestType(String testType) {
        this.testType = testType;
    }

    public String getTestType() {
        return testType;
    }

    public void setTestType(String testType) {
        this.testType = testType;
    }
}
