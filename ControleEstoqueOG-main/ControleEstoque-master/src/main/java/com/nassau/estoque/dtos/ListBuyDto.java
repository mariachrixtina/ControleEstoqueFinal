package com.nassau.estoque.dtos;

import java.util.ArrayList;
import java.util.List;

import com.nassau.estoque.models.BuyModel;

public class ListBuyDto extends BasicDTO{
	public int quantidadeTotal;
	public List<BuyModel> compras;
	public ListBuyDto() {
		super.setMensagem(new ArrayList<>());
	}
}
