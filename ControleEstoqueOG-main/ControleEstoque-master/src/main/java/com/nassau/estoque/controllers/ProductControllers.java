package com.nassau.estoque.controllers;

import com.nassau.estoque.dtos.ProductResponseDto;
import com.nassau.estoque.dtos.ListProductDto;
import com.nassau.estoque.models.ProductModel;
import com.nassau.estoque.repositories.ProductRepository;
import com.nassau.estoque.services.ProductServices;

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
@Api(value = "Exemplo API Produtos")
@RequestMapping("/products")
public class ProductControllers {

    @Autowired
    ProductRepository ProductRepository;
    @Autowired
    ProductServices ProductServices;
    @PostMapping
	public ResponseEntity<ProductResponseDto> cadastrar(@Valid @RequestBody ProductModel dados, BindingResult bindingResult) {
		ProductResponseDto response = new ProductResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			try {
				dados = ProductRepository.save(dados);
				response.produto = dados;
				response.getMensagem().add("produto cadastrado com sucesso");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (DataIntegrityViolationException e) {
				response.produto = dados;
				response.getMensagem().add(e.getLocalizedMessage());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
    
    @GetMapping
    public ResponseEntity<ListProductDto> listar() {
		ListProductDto response = new ListProductDto();
		response.setStatusCode("200");
		List<ProductModel> lista = (List<ProductModel>) ProductRepository.findAll();
		response.quantidadeTotal = lista.size();
		if(lista.size() == 0) {
			response.getMensagem().add("Nenhum produto Encontrado");
		} else {
			response.produtos = lista; 
		}
		return new ResponseEntity<>(
				
				response, HttpStatus.OK);
	}
    @GetMapping("/{idProduct}")
    public ResponseEntity<ProductResponseDto> getProducte(@PathVariable UUID idProduct) {
		ProductResponseDto response = new ProductResponseDto();
		response.setStatusCode("200");
		Optional<ProductModel> SearchProduct = ProductRepository.findById(idProduct);
		if (SearchProduct.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Produto não encontrado =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Produto encontrado   =)");
			response.produto = SearchProduct.get();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @DeleteMapping("/{idProduct}")
    public ResponseEntity<ProductResponseDto> delete(@PathVariable UUID idProduct) {
		ProductResponseDto response = new ProductResponseDto();
		response.setStatusCode("200");
		Optional<ProductModel> SearchProduct = ProductRepository.findById(idProduct);
		if (SearchProduct.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Produto não encontrado =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("O Produto foi deletado");
			ProductRepository.deleteById(idProduct);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @PutMapping("/{idProduct}")
    public ResponseEntity<ProductResponseDto> atualizar(@PathVariable UUID idProduct, @Valid @RequestBody ProductModel dados,
			BindingResult bindingResult) {
		ProductResponseDto response = new ProductResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			Optional<ProductModel> SearchProduct = ProductRepository.findById(idProduct);
			if (SearchProduct.isPresent()) {
				ProductModel existingProduct = SearchProduct.get();
				
				existingProduct.setName(dados.getName());
				existingProduct.setPrice(dados.getPrice());
                existingProduct.setQuantity(dados.getQuantity());
				existingProduct.setSupplier(dados.getSupplier());
				ProductRepository.save(existingProduct);
				response.getMensagem().add("Produto atualizado");
				response.produto = existingProduct;
			
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setStatusCode("199");
				response.getMensagem().add("Produto não encontrado");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
