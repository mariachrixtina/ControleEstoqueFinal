package com.nassau.estoque.services;

import com.nassau.estoque.models.SupplierModel;
import com.nassau.estoque.repositories.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class SupplierServices {
    @Autowired
    SupplierRepository supplierRepository;

    public List<SupplierModel> findAll() {
        return supplierRepository.findAll();
    }
    public SupplierModel findById(UUID idSupplier) {
        Optional<SupplierModel> s = supplierRepository.findById(idSupplier);
        return s.orElseGet(SupplierModel::new);
    }
    public SupplierModel updateDate(UUID idSupplier, SupplierModel SupplierToUpdate) {
        SupplierModel supplier = supplierRepository.getReferenceById(idSupplier);
        supplier.setCnpj(SupplierToUpdate.getCnpj());
        supplier.setName_supplier(SupplierToUpdate.getName_supplier());
        supplier.setTypeProduct(SupplierToUpdate.getTypeProduct());
        return supplierRepository.save(supplier);
    }
}
