package dev.ecommerce.backend.security.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import dev.ecommerce.backend.user.model.EUser;
import dev.ecommerce.backend.user.repository.EUserRepository;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
	@Autowired
	private EUserRepository repository;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		Optional<EUser> userOpt = repository.findByUsername(username);
		if(userOpt.isEmpty()) 
			return null;
		
		EUser currentUser = userOpt.get();
		
		return new User(currentUser.getUsername(), currentUser.getPassword(),getGrantedAuthority(currentUser) );
	}
	private List<GrantedAuthority> getGrantedAuthority(EUser currentUser) {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		currentUser.getGroups().forEach(t-> authorities.add(new SimpleGrantedAuthority(t.getName())));
		
		return authorities;
	}

}
