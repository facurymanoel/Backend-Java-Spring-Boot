package com.everymind.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.hamcrest.MatcherAssert;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.everymind.builder.ProdutoDTOBuilder;
import com.everymind.dto.ProdutoDTO;
import com.everymind.mapper.ProdutoMapper;
import com.everymind.model.Produto;
import com.everymind.repository.ProdutoRepository;

@ExtendWith(MockitoExtension.class)
public class ProdutoServiceTeste {
	
	private static final ProdutoMapper produtoMapper = ProdutoMapper.INSTANCE;
	
	@Mock //Banco de Dados fictício
	private ProdutoRepository produtoRepository;
	
	@InjectMocks //Injetando o Mock do repository para o service
	private ProdutoService produtoService;
	
	private ProdutoDTOBuilder produtoDTOBuilder;
	
	@BeforeEach
	void setUp() {
		
		produtoDTOBuilder = ProdutoDTOBuilder.builder().build();
	}
	
	@Test //Testando Método findAll() 
	void testProdutoFindAllSucess() {
		
		ProdutoDTO produtoFoundDTO = produtoDTOBuilder.buildProdutoDTO();
		Produto produtoFound = produtoMapper.toModel(produtoFoundDTO);
		when(produtoRepository.findAll()).thenReturn(Collections.singletonList(produtoFound));
		List<ProdutoDTO> foundProdutoDto = produtoService.findAll();
	}
	
	@Test //Testando Método findById()
	void testFindByIdSucess() {
		
		ProdutoDTO produtoFoundDTO = produtoDTOBuilder.buildProdutoDTO();
		Produto produtoFound = produtoMapper.toModel(produtoFoundDTO);
		when(produtoRepository.findById(produtoFound.getCodigoProduto()))
		                      .thenReturn(Optional.of(produtoFound));
		
		ProdutoDTO foundProdutoDTO = produtoService.findById(produtoFoundDTO.getCodigoProduto());
		 }
	 
	@Test //Testando Método create
	void testeCreateProdutoSucess() throws Exception {
	   
		 ProdutoDTO expectedProdutoTocreatedDTO = produtoDTOBuilder.buildProdutoDTO();
		 Produto expectedProdutoCreated = produtoMapper.toModel(expectedProdutoTocreatedDTO);
		 when(produtoRepository.save(expectedProdutoCreated)).thenReturn(expectedProdutoCreated);
		 ProdutoDTO createdProdutoDTO = produtoService.create(expectedProdutoTocreatedDTO);
		 MatcherAssert.assertThat(createdProdutoDTO, Matchers.equalTo(expectedProdutoTocreatedDTO));
			
	  }
	
	@Test //Testando Método update
	void testeUpdateProdutoSucess() {
		
		 ProdutoDTO expectedProdutoToUpdateDTO = produtoDTOBuilder.buildProdutoDTO();
		 Produto expectedProdutoUpdate = produtoMapper.toModel(expectedProdutoToUpdateDTO);
		 when(produtoRepository.findById(expectedProdutoToUpdateDTO.getCodigoProduto())).thenReturn(Optional.of(expectedProdutoUpdate));
		 when(produtoRepository.save(expectedProdutoUpdate)).thenReturn(expectedProdutoUpdate);
		 ProdutoDTO updateMessage = produtoService.update(expectedProdutoToUpdateDTO.getCodigoProduto(), expectedProdutoToUpdateDTO);
		 MatcherAssert.assertThat(updateMessage, is(equalTo(expectedProdutoToUpdateDTO)));
		
	}
	
	@Test //Testando Método delete
	void testDeleteProdutoSucess() {
		
		 ProdutoDTO produtoDeletedDTO = produtoDTOBuilder.buildProdutoDTO();
		 Produto produtoDeleted = produtoMapper.toModel(produtoDeletedDTO);
		 Long produtoId = produtoDeletedDTO.getCodigoProduto();
		 doNothing().when(produtoRepository).deleteById(produtoDeletedDTO.getCodigoProduto());
		 when(produtoRepository.findById(produtoId)).thenReturn(Optional.of(produtoDeleted));
		 produtoService.delete(produtoId);
		 verify(produtoRepository, times(1)).deleteById(produtoId);
		
	}
	
	

}
