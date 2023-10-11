package com.nassau.estoque.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "buy_products" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BuyModel implements Serializable {
    private static final Long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idbuy;
    @ManyToOne
    @JoinColumn(name = "IdClient")
    @NotBlank(message = "O ID do cliente não pode estar nulo ou em branco")
    private ClientModel cliente;
    @ManyToOne
    @JoinColumn(name = "IdProduct")
    @NotBlank(message = "O ID do produto não pode estar nulo ou em branco")
    private ProductModel produto;    
}
