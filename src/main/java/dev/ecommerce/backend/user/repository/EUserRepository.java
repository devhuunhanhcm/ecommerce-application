package dev.ecommerce.backend.user.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.user.model.EUser;
@Repository
public interface EUserRepository extends JpaRepository<EUser, UUID> {
	@Query("SELECT u FROM EUser u LEFT JOIN FETCH u.groups WHERE u.username = ?1")
	Optional<EUser> findByUsername(String username);
	Optional<EUser> findByEmail(String email);

}
