package com.everymind.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.everymind.dto.ProdutoDTO;
import com.everymind.model.Produto;

@Mapper
public interface ProdutoMapper {
	
	ProdutoMapper INSTANCE = Mappers.getMapper(ProdutoMapper.class);
	
	//Converte DTO para Entidade
	Produto toModel(ProdutoDTO produtoDTO);
	
	//Converte Entidade para DTO
	ProdutoDTO toDTO(Produto produto);
	
	 

}
