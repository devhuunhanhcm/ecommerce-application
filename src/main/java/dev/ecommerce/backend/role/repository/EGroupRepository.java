package dev.ecommerce.backend.role.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.role.model.EGroup;

@Repository
public interface EGroupRepository extends JpaRepository<EGroup, UUID> {

	Optional<EGroup> findByName(String roleName);

}
