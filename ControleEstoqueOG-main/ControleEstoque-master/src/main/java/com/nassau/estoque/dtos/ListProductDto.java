package com.nassau.estoque.dtos;

import java.util.ArrayList;
import java.util.List;

import com.nassau.estoque.models.ProductModel;

public class ListProductDto extends BasicDTO{
	public int quantidadeTotal;
	public List<ProductModel> produtos;
	public ListProductDto() {
		super.setMensagem(new ArrayList<>());
	}
}
