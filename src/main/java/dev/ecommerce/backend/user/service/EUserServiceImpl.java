package dev.ecommerce.backend.user.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.user.dto.EUserDTO;
import dev.ecommerce.backend.user.dto.EUserLoginDTO;
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

}
