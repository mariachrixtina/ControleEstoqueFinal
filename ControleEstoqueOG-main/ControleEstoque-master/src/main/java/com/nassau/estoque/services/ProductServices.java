package com.nassau.estoque.services;

import com.nassau.estoque.models.ProductModel;
import com.nassau.estoque.repositories.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class ProductServices {
    @Autowired
    ProductRepository productRepository;

    public List<ProductModel> findAll() {
        return productRepository.findAll();
    }
    public ProductModel findById(UUID idProduct) {
        Optional<ProductModel> p = productRepository.findById(idProduct);
        return p.orElseGet(ProductModel::new);
    }
    public ProductModel updateDate(UUID idProduct, ProductModel productToUpdate) {
        ProductModel product = productRepository.getReferenceById(idProduct);
        product.setName(productToUpdate.getName());
        product.setPrice (productToUpdate.getPrice());
        product.setQuantity(productToUpdate.getQuantity());
        product.setSupplier(productToUpdate.getSupplier());
        return productRepository.save(product);
    }
}
