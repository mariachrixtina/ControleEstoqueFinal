package com.nassau.estoque.dtos;

import java.util.ArrayList;

import com.nassau.estoque.models.ClientModel;

public class ClientResponseDto extends BasicDTO{
	public ClientModel cliente;
	public ClientResponseDto() {
		super.setMensagem(new ArrayList<>());
	}
}
