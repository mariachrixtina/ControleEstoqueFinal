package com.nassau.estoque.models;

import jakarta.persistence.*;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "tb_products" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProductModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idProduct;
    private String name;
    private Float price;
    private Integer quantity;
    @ManyToOne
    @JoinColumn(name = "IdFornecedor") // Nome da coluna de chave estrangeira na tabela de produtos
    private SupplierModel supplier; // Campo para representar o fornecedor
}
