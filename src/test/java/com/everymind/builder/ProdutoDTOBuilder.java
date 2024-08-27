package com.everymind.builder;

import java.math.BigDecimal;
import java.math.MathContext;

import com.everymind.dto.ProdutoDTO;

import lombok.Builder;

@Builder
public class ProdutoDTOBuilder {
	
	@Builder.Default
	private Long codigoProduto = 1L;
	
	private String nomeProduto = "Calça";
	
	private String descricaoProduto = "calça jeans azul";
	
	private BigDecimal precoProduto = new BigDecimal("50.10");
	
	public ProdutoDTO buildProdutoDTO() {
		
		return new ProdutoDTO(
				codigoProduto,
				nomeProduto,
				descricaoProduto,
				precoProduto);
	}
	
	 

}
