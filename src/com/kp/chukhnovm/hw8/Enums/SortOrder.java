package com.kp.chukhnovm.hw8.Enums;

public enum SortOrder {
    ASC("Ascending"), DESC("Descending");

    private final String description;

    private SortOrder(String description) {
        this.description = description;
    }

    public String getDescription() {
        return description;
    }

    @Override
    public String toString() {
        return "SortOrder{" +
                "description='" + description + '\'' +
                '}';
    }
}
