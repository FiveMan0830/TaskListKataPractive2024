package com.codurance.training.tasks.usecase.port.out;

public class DueDateData {
    private final String value;

    public DueDateData(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static DueDateData of(String date) {
        return new DueDateData(date);
    }
}
