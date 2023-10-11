package com.nassau.estoque.models;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.io.Serializable;
import java.util.UUID;

import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.br.CPF;

@Entity
@Table(name = "client_products" )
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientModel implements Serializable {
    private static final Long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID idClient;
    @Column(name = "cpf", columnDefinition = "VARCHAR(11)", unique = true, nullable = false)
	@NotBlank(message = "O CPF não pode estar nulo ou em branco")
	@Length(min = 11, max = 11, message = "O CPF deve conter 11 números")
    @CPF
	public String cpf;
    private String nameClient;

}