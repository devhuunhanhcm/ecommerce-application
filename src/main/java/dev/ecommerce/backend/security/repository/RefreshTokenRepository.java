package dev.ecommerce.backend.security.repository;

import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.security.model.RefreshToken;
import dev.ecommerce.backend.user.model.EUser;

@Repository
public interface RefreshTokenRepository extends JpaRepository<RefreshToken, UUID>{

	Optional<RefreshToken> findByToken(String token);
	
	@Transactional
	@Modifying
	@Query("DELETE FROM RefreshToken r WHERE r.user = ?1 AND r.id = ?2")
	int deleteByUserAndId(EUser user, UUID id);
}
