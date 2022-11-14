package dev.ecommerce.backend.role.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.role.dto.EProgramDTO;
import dev.ecommerce.backend.role.mapper.EProgramMapper;
import dev.ecommerce.backend.role.model.EProgram;
import dev.ecommerce.backend.role.repository.EProgramRepository;

@Service
public class EProgramServiceImpl implements EProgramService {
	@Autowired
	private EProgramRepository repository;
	
	@Override
	public List<EProgramDTO> findAll() {
		return repository.findAll()
						.stream()
						.map(t -> EProgramMapper.INSTANCE.toDTO(t))
						.collect(Collectors.toList());
	}

	@Override
	public EProgramDTO createProgram(EProgramDTO dto) {
		if(dto == null)
			return null;
		EProgram programDto = EProgramMapper.INSTANCE.toEntity(dto);
		EProgram newProgram= repository.save(programDto);
		return EProgramMapper.INSTANCE.toDTO(newProgram);
	}

	@Override
	public EProgramDTO updateProgram(String programId, EProgramDTO dto) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String deleteProgram(String programId) {
		// TODO Auto-generated method stub
		return null;
	}

}
