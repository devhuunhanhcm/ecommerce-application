package dev.ecommerce.backend.security.authorization;

import java.util.List;

import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import dev.ecommerce.backend.role.model.EProgram;
import dev.ecommerce.backend.role.repository.EProgramRepository;
import dev.ecommerce.backend.security.exception.UnAuthorizerException;

@Component
@Aspect
public class AuthorizationAspect {
	@Autowired
	private EProgramRepository programRepository;
	
	@Before("@annotation(program)")
	public void programAuthorizer(EAnnotationProgram program) {
		String username = getUsername();
		String programName = program.value();
		boolean isAuthorizer = checkPermission(username,programName);
		if(!isAuthorizer)
			throw new UnAuthorizerException();
	}

	private boolean checkPermission(String username, String programName) {
		List<EProgram> programs = programRepository.findProgramByNameAndUsername(programName,username);
		return programs.size() > 0;
	}

	private String getUsername() {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if(auth == null)
			return null;
		if(auth.getPrincipal() instanceof String principal)
			return principal;
		
		UserDetails currentAuditor = (UserDetails) auth.getPrincipal();
		return currentAuditor.getUsername();
	}
	
}
