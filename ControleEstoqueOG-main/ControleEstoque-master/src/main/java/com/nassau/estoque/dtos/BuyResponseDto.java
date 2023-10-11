package com.nassau.estoque.dtos;

import java.util.ArrayList;

import com.nassau.estoque.models.BuyModel;

public class BuyResponseDto extends BasicDTO{
	public BuyModel compra;
	public BuyResponseDto() {
		super.setMensagem(new ArrayList<>());
	}
}
