package io09.excel;

public enum DateFormat {

    DATE("yyyy/MM/dd"),

    DATE_TIME("yyyy-MM-ddThh:mm:ss");
    public final String value;

    DateFormat(String value) {
        this.value = value;
    }

}
