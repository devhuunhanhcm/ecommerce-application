package dev.ecommerce.backend.product.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.product.model.EProduct;
@Repository
public interface EProductRepository extends JpaRepository<EProduct, UUID> {
	Optional<EProduct> findByName(String productName);
}
