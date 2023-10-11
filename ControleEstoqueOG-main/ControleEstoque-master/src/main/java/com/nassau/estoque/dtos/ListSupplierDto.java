package com.nassau.estoque.dtos;

import java.util.ArrayList;
import java.util.List;

import com.nassau.estoque.models.SupplierModel;

public class ListSupplierDto extends BasicDTO{
	public int quantidadeTotal;
	public List<SupplierModel> fornecedores;
	public ListSupplierDto() {
		super.setMensagem(new ArrayList<>());
	}
}
