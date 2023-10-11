package com.nassau.estoque.dtos;

import java.util.ArrayList;
import java.util.List;

import com.nassau.estoque.models.ClientModel;

public class ListClientDto extends BasicDTO{
	public int quantidadeTotal;
	public List<ClientModel> clientes;
	public ListClientDto() {
		super.setMensagem(new ArrayList<>());
	}
}
