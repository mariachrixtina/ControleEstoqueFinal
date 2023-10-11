package com.nassau.estoque.enums;

public enum EnumProduct {
    NOT_FOUND("Produto não encontrado no estoque."),
    CREATE_SUCCESSFULL("Produto criado com sucesso."),
    UPDATE_SUCCESSFULL("Produto atualizado com sucesso."),
    DELETE_SUCCESSFULL("Produto deletado com sucesso.");

    final private String value;

    private EnumProduct(String value) {
        this.value = value;
    }
}
