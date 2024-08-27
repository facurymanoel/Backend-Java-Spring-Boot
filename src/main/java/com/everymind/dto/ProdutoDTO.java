package com.everymind.dto;

import java.math.BigDecimal;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProdutoDTO {
	
	private Long codigoProduto;
	
	@NotBlank
	@Size(max = 60)
	private String nomeProduto;
	
	@NotBlank
	@Size(max = 255)
	private String descricaoProduto;
	
	 private BigDecimal precoProduto;

}
