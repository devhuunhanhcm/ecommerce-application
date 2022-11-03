package dev.ecommerce.backend.security.jwt;

import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.SignatureException;
import io.jsonwebtoken.UnsupportedJwtException;

@Component
public class JwtHelper {
	private static final String PREFIX = "Bearer ";
	private static final long EXPIRE_DURATION = 1 * 60 * 60 * 1000; // 1 hour
	
	@Value("${app.jwt.secret}")
	private String SECRET_KEY;
	
	public String generateJwtToken(String username) {
		
		Date currentDate = new Date();
		return Jwts.builder()
				.setSubject(username)
				.setIssuedAt(currentDate)
				.setExpiration(new Date(currentDate.getTime() + EXPIRE_DURATION))
				.signWith(SignatureAlgorithm.HS512, SECRET_KEY)
				.compact();
	}
	
    
    public boolean validateAccessToken(String token) {
    	try {
			Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
			return true;
		}catch(UnsupportedJwtException ex1) {
			System.out.println("Jwt is not supported: "+ex1);
		}catch(MalformedJwtException ex2) {
			System.out.println("Invalid token: "+ex2);
		}catch(SignatureException  ex3) {
			System.out.println("Invalid token: "+ex3);
		}catch(ExpiredJwtException  ex4) {
			System.out.println("Jwt is expried"+ex4);
		}catch(IllegalArgumentException  ex5) {
			System.out.println("Jwt claims is empty: "+ex5);
		}
		
		return false;
    }


	public String getToken(HttpServletRequest request) {
		String jwt = request.getHeader("Authorization");
		if(jwt == null)
			return jwt;
				
		return jwt.substring(PREFIX.length(),jwt.length());
	}


	public String getUsername(String token) {
		
		return Jwts.parser()
					.setSigningKey(SECRET_KEY)
					.parseClaimsJws(token)
					.getBody()
					.getSubject();
	}
}
