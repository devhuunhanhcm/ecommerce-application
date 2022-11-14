package dev.ecommerce.backend.role.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;
import dev.ecommerce.backend.role.model.EGroup;

@Repository
public interface EGroupRepository extends JpaRepository<EGroup, UUID> {

	Optional<EGroup> findByName(String roleName);

	@Query("SELECT g from EGroup g JOIN g.roles WHERE g.id = ?1")
	EGroup findGroupWithRoleById(UUID id);

}
