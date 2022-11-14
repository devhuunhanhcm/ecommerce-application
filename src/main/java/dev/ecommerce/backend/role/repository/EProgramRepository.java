package dev.ecommerce.backend.role.repository;

import java.util.List;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import dev.ecommerce.backend.role.model.EProgram;

@Repository
public interface EProgramRepository extends JpaRepository<EProgram, UUID> {
	@Query("""
			SELECT p
			FROM EProgram p LEFT JOIN p.roles r
							LEFT JOIN r.groups g
							LEFT JOIN g.users u
			WHERE p.name = ?1 AND u.username = ?2
			""")
	List<EProgram> findProgramByNameAndUsername(String programName, String username);

}
