package com.nassau.estoque.services;

import com.nassau.estoque.models.BuyModel;
import com.nassau.estoque.repositories.BuyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class BuyServices {
    @Autowired
    BuyRepository buyRepository;

    public List<BuyModel> findAll() {
        return buyRepository.findAll();
    }
    public BuyModel findById(UUID idBuy) {
        Optional<BuyModel> b = buyRepository.findById(idBuy);
        return b.orElseGet(BuyModel::new);
    }
    public BuyModel updateDate(UUID idBuy, BuyModel BuyToUpdate) {
        BuyModel buy = buyRepository.getReferenceById(idBuy);
        buy.setCliente(BuyToUpdate.getCliente());
        buy.setProduto (BuyToUpdate.getProduto());
        return buyRepository.save(buy);
    }
}
