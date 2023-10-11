package com.nassau.estoque.controllers;

import com.nassau.estoque.dtos.SupplierResponseDto;
import com.nassau.estoque.dtos.ListSupplierDto;
import com.nassau.estoque.models.SupplierModel;
import com.nassau.estoque.repositories.SupplierRepository;
import com.nassau.estoque.services.SupplierServices;

import io.swagger.annotations.Api;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;



import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@Api(value = "Exemplo API Fornecedores")
@RequestMapping("/suppliers")

public class SupplierControllers {

    @Autowired
    SupplierRepository SupplierRepository;
    @Autowired
    SupplierServices SupplierServices;
    @PostMapping
	public ResponseEntity<SupplierResponseDto> cadastrar(@Valid @RequestBody SupplierModel dados, BindingResult bindingResult) {
		SupplierResponseDto response = new SupplierResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			try {
				dados = SupplierRepository.save(dados);
				response.fornecedor = dados;
				response.getMensagem().add("Fornecedor cadastrado com sucesso");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (DataIntegrityViolationException e) {
				response.fornecedor = dados;
				response.getMensagem().add(e.getLocalizedMessage());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
    
    @GetMapping
    public ResponseEntity<ListSupplierDto> listar() {
		ListSupplierDto response = new ListSupplierDto();
		response.setStatusCode("200");
		List<SupplierModel> lista = (List<SupplierModel>) SupplierRepository.findAll();
		response.quantidadeTotal = lista.size();
		if(lista.size() == 0) {
			response.getMensagem().add("Nenhum fornecedor encontrado");
		} else {
			response.fornecedores = lista; 
		}
		return new ResponseEntity<>(
				
				response, HttpStatus.OK);
	}
    @GetMapping("/{idSupplier}")
    public ResponseEntity<SupplierResponseDto> getSuppliere(@PathVariable UUID idSupplier) {
		SupplierResponseDto response = new SupplierResponseDto();
		response.setStatusCode("200");
		Optional<SupplierModel> SearchSupplier = SupplierRepository.findById(idSupplier);
		if (SearchSupplier.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Fornecedor não encontrado =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Fornecedor encontrado   =)");
			response.fornecedor = SearchSupplier.get();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @DeleteMapping("/{idSupplier}")
    public ResponseEntity<SupplierResponseDto> delete(@PathVariable UUID idSupplier) {
		SupplierResponseDto response = new SupplierResponseDto();
		response.setStatusCode("200");
		Optional<SupplierModel> SearchSupplier = SupplierRepository.findById(idSupplier);
		if (SearchSupplier.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Fornecedor não encontrado =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("O Fornecedor foi deletado");
			SupplierRepository.deleteById(idSupplier);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @PutMapping("/{idSupplier}")
    public ResponseEntity<SupplierResponseDto> atualizar(@PathVariable UUID idSupplier, @Valid @RequestBody SupplierModel dados,
			BindingResult bindingResult) {
		SupplierResponseDto response = new SupplierResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			Optional<SupplierModel> SearchSupplier = SupplierRepository.findById(idSupplier);
			if (SearchSupplier.isPresent()) {
				SupplierModel existingSupplier = SearchSupplier.get();
				
				existingSupplier.setCnpj(dados.getCnpj());
				existingSupplier.setName_supplier(dados.getName_supplier());
                existingSupplier.setTypeProduct(dados.getTypeProduct());
				SupplierRepository.save(existingSupplier);
				response.getMensagem().add("fornecedor atualizado");
				response.fornecedor = existingSupplier;
			
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setStatusCode("199");
				response.getMensagem().add("fornecedor não encontrado");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
