package dev.ecommerce.backend.security.repository;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import dev.ecommerce.backend.user.model.EUser;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.UUID;

@SpringBootTest
public class RefreshTokenRepositoryTest {
	@Autowired
	private RefreshTokenRepository repository;
	
	@Test
	public void shouldDeleteRefreshTokenWithUserId() {
//		assertDoesNotThrow(() -> {
//			UUID id = UUID.fromString("2006a33f-4cee-48d1-a4e7-2319e28c7b0b");
//			EUser user = EUser.builder().id(UUID.fromString("e264968f-9c9d-4427-80cb-2c057d2a2be6")).build();
//			repository.deleteByUserAndId(user,id);
//		});
	}
}
