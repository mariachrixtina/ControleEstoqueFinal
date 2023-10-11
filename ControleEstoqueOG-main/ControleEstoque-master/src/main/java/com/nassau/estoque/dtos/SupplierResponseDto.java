package com.nassau.estoque.dtos;

import java.util.ArrayList;

import com.nassau.estoque.models.SupplierModel;

public class SupplierResponseDto extends BasicDTO{
	public SupplierModel fornecedor;
	public SupplierResponseDto() {
		super.setMensagem(new ArrayList<>());
	}
}
