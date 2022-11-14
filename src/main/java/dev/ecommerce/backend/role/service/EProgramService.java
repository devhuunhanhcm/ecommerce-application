package dev.ecommerce.backend.role.service;

import java.util.List;

import dev.ecommerce.backend.role.dto.EProgramDTO;

public interface EProgramService {
	List<EProgramDTO> findAll();
	EProgramDTO createProgram(EProgramDTO dto);
	EProgramDTO updateProgram(String programId,EProgramDTO dto);
	String deleteProgram(String programId);
}
