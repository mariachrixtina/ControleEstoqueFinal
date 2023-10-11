package com.nassau.estoque.services;

import com.nassau.estoque.models.ClientModel;
import com.nassau.estoque.repositories.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ClientServices {
    @Autowired
    ClientRepository clientRepository;

    public List<ClientModel> findAll() {
        return clientRepository.findAll();
    }
    public ClientModel findById(UUID idClient) {
        Optional<ClientModel> c = clientRepository.findById(idClient);
        return c.orElseGet(ClientModel::new);
    }
    public void deleteClientById(UUID idClient) {
        clientRepository.deleteById(idClient);
    }
    

    public ClientModel updateDate(UUID idClient, ClientModel ClientToUpdate) {
        ClientModel client = clientRepository.getReferenceById(idClient);
       client.setCpf(ClientToUpdate.getCpf());
       client.setNameClient(ClientToUpdate.getNameClient());
        return clientRepository.save(client);
    }
}
