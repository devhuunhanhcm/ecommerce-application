package dev.ecommerce.backend.role.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.role.model.ERole;

@Repository
public interface ERoleRepository extends JpaRepository<ERole, UUID> {
	Optional<ERole> findByName(String roleName);

}
