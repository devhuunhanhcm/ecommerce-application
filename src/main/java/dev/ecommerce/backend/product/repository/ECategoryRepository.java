package dev.ecommerce.backend.product.repository;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.product.model.ECategory;

@Repository
public interface ECategoryRepository extends JpaRepository<ECategory, UUID> {

}
