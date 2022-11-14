package dev.ecommerce.backend.role.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import javax.validation.Valid;

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

	@Override
	public ERoleDTO updateRole(String roleId, @Valid ERoleDTO dto) {
		Optional<ERole> roleOpt = repository.findById(UUID.fromString(roleId));
		if(roleOpt.isEmpty())
			return null;
		
		ERole role = roleOpt.get();
		if(!dto.getName().equals(role.getName())){
			Optional<ERole> roleNameExisted = repository.findByName(dto.getName());
			if(roleNameExisted.isPresent())
				return null;
		}
		role.setName(dto.getName());
		role.setDescription(dto.getDescription());
		repository.save(role);

		return ERoleMapper.INSTANCE.toDTO(role);
	}

	@Override
	public ERoleDTO findById(String roleId) {
		Optional<ERole> roleOpt = repository.findById(UUID.fromString(roleId));
		if(roleOpt.isEmpty())
			return null;
		
		return ERoleMapper.INSTANCE.toDTO(roleOpt.get());
	}

}
