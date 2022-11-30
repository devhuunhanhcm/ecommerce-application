package dev.ecommerce.backend.product.dto;

import java.util.Set;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

@Setter
@Getter
@NoArgsConstructor
@SuperBuilder
public class ECategoryWithProductDTO {
	private String name;
	private Set<EProductDTO> products;
}
