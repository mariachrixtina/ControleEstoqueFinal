package com.nassau.estoque.enums;

public enum EnumSupplier {
    NOT_FOUND("Fornecedor não encontrado."),
    CREATE_SUCCESSFUL("Fornecedor criado com sucesso."),
    UPDATE_SUCCESSFUL("Fornecedor atualizado com sucesso."),
    DELETE_SUCCESSFUL("Fornecedor deletado com sucesso.");

    private final String value;

    private EnumSupplier(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
