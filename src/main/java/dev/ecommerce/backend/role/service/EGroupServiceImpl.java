package dev.ecommerce.backend.role.service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.role.dto.EGroupDTO;
import dev.ecommerce.backend.role.dto.EGroupWithRolesDTO;
import dev.ecommerce.backend.role.exception.IdIsNullException;
import dev.ecommerce.backend.role.mapper.EGroupMapper;
import dev.ecommerce.backend.role.model.EGroup;
import dev.ecommerce.backend.role.model.ERole;
import dev.ecommerce.backend.role.repository.EGroupRepository;
import dev.ecommerce.backend.role.repository.ERoleRepository;

@Service
public class EGroupServiceImpl implements EGroupService{
	@Autowired
	private EGroupRepository repository;
	
	@Autowired
	private ERoleRepository roleRepository;
	
	@Override
	public List<EGroupDTO> findAll() {
		return repository.findAll()
						.stream()
						.map(t->EGroupMapper.INSTANCE.toDTO(t))
						.collect(Collectors.toList());
	}

	@Override
	public EGroupDTO createNewGroup(EGroupDTO dto) {
		EGroup group = repository.save(EGroupMapper.INSTANCE.toEntity(dto));
		return EGroupMapper.INSTANCE.toDTO(group);
	}

	@Override
	public EGroupWithRolesDTO addRole(String groupId, String roleId) {
		Optional<ERole> roleOpt;
		Optional<EGroup> groupOpt;
		try {
			groupOpt = repository.findById(UUID.fromString(groupId));
			roleOpt = roleRepository.findById(UUID.fromString(roleId));
		}catch(IllegalArgumentException e) {
			throw new IdIsNullException("Id is not correct");
		}
		if(groupOpt.isEmpty() || roleOpt.isEmpty())
			return null;
		EGroup group = groupOpt.get();
		group.addRole(roleOpt.get());
		repository.save(group);
		
		return EGroupMapper.INSTANCE.toGroupWithRolesDTO(group);
	}
	
}
