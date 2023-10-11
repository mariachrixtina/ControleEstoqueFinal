package com.nassau.estoque.controllers;

import com.nassau.estoque.dtos.BuyResponseDto;
import com.nassau.estoque.dtos.ListBuyDto;
import com.nassau.estoque.models.BuyModel;
import com.nassau.estoque.repositories.BuyRepository;
import com.nassau.estoque.services.BuyServices;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/buys")
@Api(value = "Exemplo API Compras")
public class BuyControllers {

    @Autowired
    BuyRepository BuyRepository;
    @Autowired
    BuyServices BuyServices;
    @PostMapping
	public ResponseEntity<BuyResponseDto> cadastrar(@Valid @RequestBody BuyModel dados, BindingResult bindingResult) {
		BuyResponseDto response = new BuyResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			try {
				dados = BuyRepository.save(dados);
				response.compra = dados;
				response.getMensagem().add("Compra cadastrada com sucesso");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (DataIntegrityViolationException e) {
				response.compra = dados;
				response.getMensagem().add(e.getLocalizedMessage());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
    
    @GetMapping
	@ApiOperation(value = "Obter Todos Exemplos")
    public ResponseEntity<ListBuyDto> listar() {
		ListBuyDto response = new ListBuyDto();
		response.setStatusCode("200");
		List<BuyModel> lista = (List<BuyModel>) BuyRepository.findAll();
		response.quantidadeTotal = lista.size();
		if(lista.size() == 0) {
			response.getMensagem().add("Nenhuma compra Encontrado");
		} else {
			response.compras = lista; 
		}
		return new ResponseEntity<>(
				
				response, HttpStatus.OK);
	}
    @GetMapping("/{idBuy}")
    public ResponseEntity<BuyResponseDto> getBuye(@PathVariable UUID idBuy) {
		BuyResponseDto response = new BuyResponseDto();
		response.setStatusCode("200");
		Optional<BuyModel> SearchBuy = BuyRepository.findById(idBuy);
		if (SearchBuy.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Compra não encontrada =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Compra encontrada   =)");
			response.compra = SearchBuy.get();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @DeleteMapping("/{idBuy}")
    public ResponseEntity<BuyResponseDto> delete(@PathVariable UUID idBuy) {
		BuyResponseDto response = new BuyResponseDto();
		response.setStatusCode("200");
		Optional<BuyModel> SearchBuy = BuyRepository.findById(idBuy);
		if (SearchBuy.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Compra não encontrada =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Compra deletada");
			BuyRepository.deleteById(idBuy);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @PutMapping("/{idBuy}")
    public ResponseEntity<BuyResponseDto> atualizar(@PathVariable UUID idBuy, @Valid @RequestBody BuyModel dados,
			BindingResult bindingResult) {
		BuyResponseDto response = new BuyResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			Optional<BuyModel> SearchBuy = BuyRepository.findById(idBuy);
			if (SearchBuy.isPresent()) {
				BuyModel existingBuy = SearchBuy.get();
				existingBuy.setCliente(dados.getCliente());
				existingBuy.setProduto(dados.getProduto());
				BuyRepository.save(existingBuy);
				response.getMensagem().add("Compra atualizada");
				response.compra = existingBuy;
			
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setStatusCode("199");
				response.getMensagem().add("Compra não encontrada");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
