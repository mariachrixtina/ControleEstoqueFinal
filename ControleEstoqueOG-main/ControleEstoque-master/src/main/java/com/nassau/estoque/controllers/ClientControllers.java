package com.nassau.estoque.controllers;

import com.nassau.estoque.dtos.ClientResponseDto;
import com.nassau.estoque.dtos.ListClientDto;
import com.nassau.estoque.models.ClientModel;
import com.nassau.estoque.repositories.ClientRepository;
import com.nassau.estoque.services.ClientServices;

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
@Api(value = "Exemplo API Clientes")
@RequestMapping("/clients")
public class ClientControllers {

    @Autowired
    ClientRepository clientRepository;
    @Autowired
    ClientServices clientServices;
    @PostMapping
	public ResponseEntity<ClientResponseDto> cadastrar(@Valid @RequestBody ClientModel dados, BindingResult bindingResult) {
		ClientResponseDto response = new ClientResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			try {
				dados = clientRepository.save(dados);
				response.cliente = dados;
				response.getMensagem().add("Cliente cadastrado com sucesso");
				return new ResponseEntity<>(response, HttpStatus.OK);
			} catch (DataIntegrityViolationException e) {
				response.cliente = dados;
				response.getMensagem().add(e.getLocalizedMessage());
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
    
    @GetMapping
    public ResponseEntity<ListClientDto> listar() {
		ListClientDto response = new ListClientDto();
		response.setStatusCode("200");
		List<ClientModel> lista = (List<ClientModel>) clientRepository.findAll();
		response.quantidadeTotal = lista.size();
		if(lista.size() == 0) {
			response.getMensagem().add("Nenhum Cliente Encontrado");
		} else {
			response.clientes = lista; 
		}
		return new ResponseEntity<>(
				
				response, HttpStatus.OK);
	}
    @GetMapping("/{idClient}")
    public ResponseEntity<ClientResponseDto> getCliente(@PathVariable UUID idClient) {
		ClientResponseDto response = new ClientResponseDto();
		response.setStatusCode("200");
		Optional<ClientModel> SearchClient = clientRepository.findById(idClient);
		if (SearchClient.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Cliente não encontrado =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("Cliente encontrado   =)");
			response.cliente = SearchClient.get();
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @DeleteMapping("/{idClient}")
    public ResponseEntity<ClientResponseDto> delete(@PathVariable UUID idClient) {
		ClientResponseDto response = new ClientResponseDto();
		response.setStatusCode("200");
		Optional<ClientModel> SearchClient = clientRepository.findById(idClient);
		if (SearchClient.isPresent() == false) {
			response.setStatusCode("199");
			response.getMensagem().add("Cliente não encontrado =( ");
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			response.getMensagem().add("O Cliente foi deletado");
			clientRepository.deleteById(idClient);
			return new ResponseEntity<>(response, HttpStatus.OK);
		}
	}
    @PutMapping("/{idClient}")
    public ResponseEntity<ClientResponseDto> atualizar(@PathVariable UUID idClient, @Valid @RequestBody ClientModel dados,
			BindingResult bindingResult) {
		ClientResponseDto response = new ClientResponseDto();
		response.setStatusCode("200");
		if (bindingResult.hasErrors()) {
			response.setStatusCode("199");
			for (ObjectError obj : bindingResult.getAllErrors()) {
				response.getMensagem().add(obj.getDefaultMessage());
			}
			return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
		} else {
			Optional<ClientModel> SearchClient = clientRepository.findById(idClient);
			if (SearchClient.isPresent()) {
				ClientModel existingClient = SearchClient.get();
				
				existingClient.setCpf(dados.getCpf());
				existingClient.setNameClient(dados.getNameClient());
				clientRepository.save(existingClient);
				response.getMensagem().add("Cliente atualizado");
				response.cliente = existingClient;
			
				return new ResponseEntity<>(response, HttpStatus.OK);
			} else {
				response.setStatusCode("199");
				response.getMensagem().add("Cliente não encontrado");
				return new ResponseEntity<>(response, HttpStatus.BAD_REQUEST);
			}
		}
	}
}
