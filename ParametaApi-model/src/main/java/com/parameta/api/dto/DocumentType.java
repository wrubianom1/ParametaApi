package com.parameta.api.dto;

public enum DocumentType {
    CEDULA(1),
    PASSPORT(2),
    IDENTITY_TARGET(3);

    private final Integer type;

    DocumentType(Integer type) {
        this.type = type;
    }

    public Integer getType() {
        return type;
    }
}

