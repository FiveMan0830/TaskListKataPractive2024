package com.codurance.training.tasks.entity;

public class DueDate {
    private final String value;

    public DueDate(String value) {
        this.value = value;
    }

    public String value() {
        return value;
    }

    public static DueDate of(String date) {
        return new DueDate(date);
    }
}
