package com.nassau.estoque.repositories;

import com.nassau.estoque.models.BuyModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface BuyRepository extends JpaRepository<BuyModel, UUID> {
}
