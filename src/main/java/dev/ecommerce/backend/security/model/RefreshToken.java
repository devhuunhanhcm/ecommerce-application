package dev.ecommerce.backend.security.model;


import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

import dev.ecommerce.backend.user.model.EUser;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@Entity
@Table(name="e_refresh_token")
@SuperBuilder
public class RefreshToken {
	@Id
	@Type(type="uuid-char")
	@GeneratedValue
	private UUID id;
	
	@OneToOne
	@JoinColumn(name="user_id",referencedColumnName = "id")
	private EUser user;
	
	@Column(name="token",nullable = false, unique = true)
	private String token;

	@Column(name="expiry_date",nullable = false)
	private LocalDateTime expiryDate;
	
}
