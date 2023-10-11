package com.nassau.estoque.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.validator.constraints.br.CNPJ;

@Entity
@Table(name = "supplier_products" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class SupplierModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idFornecedor;
    @Column(name = "cnpj", columnDefinition = "VARCHAR(14)", unique = true, nullable = false)
    @CNPJ
    private String cnpj;
    @NotBlank(message = "O Nome do Fornecedor não pode estar nulo ou em branco")
    private String name_supplier;
    private String typeProduct;
}