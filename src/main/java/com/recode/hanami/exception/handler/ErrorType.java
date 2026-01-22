package com.recode.hanami.exception.handler;

public enum ErrorType {

    ERRO("erro"),
    ERRO_PROCESSAMENTO("erro_processamento"),
    ERRO_INTERNO("erro_interno");

    private final String value;

    ErrorType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value;
    }
}
