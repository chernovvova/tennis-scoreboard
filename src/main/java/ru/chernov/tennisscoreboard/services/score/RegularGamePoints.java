package ru.chernov.tennisscoreboard.services.score;

import lombok.Getter;

public enum RegularGamePoints {
    ZERO("0"),
    FIFTEEN("15"),
    THIRTY("30"),
    FORTY("40"),
    ADVANTAGE("AD");

    public RegularGamePoints next() {
        if(this == ADVANTAGE) {
            throw new IllegalStateException("Can't call next() on ADVANTAGE");
        }
        else {
            return RegularGamePoints.values()[this.ordinal() + 1];
        }
    }

    @Getter
    private String code;

    private RegularGamePoints(String code) {
        this.code = code;
    }
}
