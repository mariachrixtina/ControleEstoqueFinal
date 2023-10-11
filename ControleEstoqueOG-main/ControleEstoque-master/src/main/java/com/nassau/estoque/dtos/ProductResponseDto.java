package com.nassau.estoque.dtos;

import java.util.ArrayList;

import com.nassau.estoque.models.ProductModel;

public class ProductResponseDto extends BasicDTO{
	public ProductModel produto;
	public ProductResponseDto() {
		super.setMensagem(new ArrayList<>());
	}
}
