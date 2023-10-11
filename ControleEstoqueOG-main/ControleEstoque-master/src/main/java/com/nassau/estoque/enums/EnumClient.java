package com.nassau.estoque.enums;

public enum EnumClient {
    NOT_FOUND("Cliente não encontrado."),
    CREATE_SUCCESSFUL("Cliente criado com sucesso."),
    UPDATE_SUCCESSFUL("Cliente atualizado com sucesso."),
    DELETE_SUCCESSFUL("Cliente deletado com sucesso.");

    private final String value;

    private EnumClient(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
