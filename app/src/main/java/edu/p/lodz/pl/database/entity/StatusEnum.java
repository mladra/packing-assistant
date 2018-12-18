package edu.p.lodz.pl.database.entity;

public enum StatusEnum {

    OPEN("OPEN", "Open"),
    CLOSED("CLOSED", "Closed");

    private String name;
    private String code;

    StatusEnum(String code, String name) {
        this.name = name;
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public String getCode() {
        return code;
    }
}
