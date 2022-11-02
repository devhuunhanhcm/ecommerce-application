package dev.ecommerce.backend.role.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.role.dto.ERoleDTO;
import dev.ecommerce.backend.role.mapper.ERoleMapper;
import dev.ecommerce.backend.role.model.ERole;
import dev.ecommerce.backend.role.repository.ERoleRepository;

@Service
public class ERoleServiceImpl implements ERoleService {
	
	@Autowired
	private ERoleRepository repository;
	@Override
	public List<ERoleDTO> findAll() {
		
		return repository.findAll()
				.stream()
				.map(t -> ERoleMapper.INSTANCE.toDTO(t))
				.collect(Collectors.toList());
	}

	@Override
	public ERoleDTO createNewRole(ERoleDTO dto) {
		ERole role = repository.save(ERoleMapper.INSTANCE.toEntity(dto));
		return ERoleMapper.INSTANCE.toDTO(role);
	}

}
