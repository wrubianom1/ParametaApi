package com.parameta.api.dto;

public enum PositionRoleType {
    DEVELOPER(1),
    HUMAN_RESOURCES(2),
    ARCHITECT(3);

    private final Integer type;

    PositionRoleType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}

