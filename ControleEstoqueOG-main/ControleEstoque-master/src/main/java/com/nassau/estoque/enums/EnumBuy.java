package com.nassau.estoque.enums;

public enum EnumBuy {
    NOT_FOUND("Compra não encontrada."),
    CREATE_SUCCESSFUL("Compra criada com sucesso."),
    UPDATE_SUCCESSFUL("Compra atualizada com sucesso."),
    DELETE_SUCCESSFUL("Compra deletada com sucesso.");

    private final String value;

    private EnumBuy(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
