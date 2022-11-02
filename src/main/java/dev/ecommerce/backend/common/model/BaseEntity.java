package dev.ecommerce.backend.common.model;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;
import javax.persistence.Version;

import org.hibernate.annotations.Type;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Getter
@Setter
@NoArgsConstructor
@SuperBuilder
@MappedSuperclass
public class BaseEntity implements Serializable  {
	@Id
	@Type(type="uuid-char")
	@GeneratedValue
	protected UUID id;
	
	@Version
	protected int version;
	
	@CreatedBy
	@Column(name="created_by")
	protected String createdBy;
	
	@CreatedDate
	@Column(name="created_at")
	protected LocalDateTime createAt;
	
	@LastModifiedDate
	@Column(name="last_modified_at")
	protected LocalDateTime lastModifiedAt;
	
	@LastModifiedBy
	@Column(name="last_modified_by")
	protected String lastModifiedBy;
}
