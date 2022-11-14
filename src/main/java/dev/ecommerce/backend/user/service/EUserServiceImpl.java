package dev.ecommerce.backend.user.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.role.dto.ERoleDTO;
import dev.ecommerce.backend.role.mapper.ERoleMapper;
import dev.ecommerce.backend.role.model.EGroup;
import dev.ecommerce.backend.user.dto.EUserDTO;
import dev.ecommerce.backend.user.dto.EUserDetailsDTO;
import dev.ecommerce.backend.user.dto.EUserLoginDTO;
import dev.ecommerce.backend.user.dto.EUserWithRolesDTO;
import dev.ecommerce.backend.user.mapper.EUserMapper;
import dev.ecommerce.backend.user.model.EUser;
import dev.ecommerce.backend.user.repository.EUserRepository;

@Service
public class EUserServiceImpl implements EUserService{

	@Autowired
	private EUserRepository repository;
	
	@Autowired
	private PasswordEncoder encoder;
	
	@Override
	public List<EUserDTO> findAll() {
		
		return repository.findAll()
						.stream()
						.map(t -> EUserMapper.INSTANCE.toDTO(t))
						.collect(Collectors.toList());
	}

	@Override
	public EUserLoginDTO createUser(EUserLoginDTO dto) {
		EUser user = EUserMapper.INSTANCE.toLoginEntity(dto);
		user.setPassword(encoder.encode(dto.getPassword()));
		
		EUser newUser = repository.save(user);
		
		newUser.setPassword("");
		
		return EUserMapper.INSTANCE.toLoginDTO(newUser);
	}

	@Override
	public EUserWithRolesDTO findUserWithRolesByUsername(String username) {
		if(username == null)
			return null;
		EUser dto = repository.findUserWithRolesByUsername(username);
		return EUserWithRolesDTO.builder()
						.id(dto.getId())
						.username(dto.getUsername())
						.displayName(dto.getDisplayName())
						.email(dto.getEmail())
						.roles(getRolesDTO(dto))
						.build();
	}

	private List<ERoleDTO> getRolesDTO(EUser user) {
		List<ERoleDTO> roles = new ArrayList<ERoleDTO>();
		for (EGroup group : user.getGroups()) {
			group.getRoles().stream().forEach(t -> {
				if(!roles.contains(t))
					roles.add(ERoleMapper.INSTANCE.toDTO(t));
			});
		}
		return roles;
	}

	@Override
	public EUserDetailsDTO findUserByUsername(String username) {
		Optional<EUser> userOtp = repository.findByUsername(username);
		return EUserMapper.INSTANCE.toUserDetailsDTO(userOtp.get());
	}

}
