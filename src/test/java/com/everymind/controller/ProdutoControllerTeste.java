package com.everymind.controller;

import static org.mockito.Mockito.doNothing;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.Collections;

import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.web.PageableHandlerMethodArgumentResolver;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import com.everymind.builder.ProdutoDTOBuilder;
import com.everymind.dto.ProdutoDTO;
import com.everymind.service.ProdutoService;
import com.everymind.utils.JsonConversionUtils;

 

@ExtendWith(MockitoExtension.class)
public class ProdutoControllerTeste {
	
	private final static String PRODUTO_API_URL_PATH = "/produtos";
	
	private MockMvc mockMvc;
	
	@Mock
	ProdutoService produtoService;
	
	@InjectMocks
	ProdutoController produtoController;
	
	private ProdutoDTOBuilder produtoDTOBuilder;
	
	@BeforeEach
	void setUp() {
		
		produtoDTOBuilder = ProdutoDTOBuilder.builder().build();
		mockMvc = MockMvcBuilders.standaloneSetup(produtoController)
				                 .setCustomArgumentResolvers(new PageableHandlerMethodArgumentResolver())
				                 .setViewResolvers((s, locale) -> new MappingJackson2JsonView())
				                 .build();
		}
	
	@Test
	void produtoFindAllOkStatus() throws Exception {
		
		ProdutoDTO produtoDTO = produtoDTOBuilder.buildProdutoDTO();
		Mockito.when(produtoService.findAll()).thenReturn(Collections.singletonList(produtoDTO));
		mockMvc.perform(MockMvcRequestBuilders.get(PRODUTO_API_URL_PATH)
				                              .contentType(MediaType.APPLICATION_JSON))
		                                      .andExpect(status().isOk())
		                                      .andExpect(jsonPath("$[0].codigoProduto", Matchers.is(produtoDTO.getCodigoProduto().intValue())))
		                                      .andExpect(jsonPath("$[0].nomeProduto", Matchers.is(produtoDTO.getNomeProduto())))
		                                      .andExpect(jsonPath("$[0].descricaoProduto", Matchers.is(produtoDTO.getDescricaoProduto())))
		                                      .andExpect(jsonPath("$[0].precoProduto", Matchers.is(produtoDTO.getPrecoProduto())));
		
		}
	
	@Test
	void produtoFindByIdOkStatus() throws Exception {
		
		ProdutoDTO produtoDTO = produtoDTOBuilder.buildProdutoDTO();
		Long produtoDtoId = produtoDTO.getCodigoProduto();
		Mockito.when(produtoService.findById(produtoDtoId)).thenReturn(produtoDTO);
		mockMvc.perform(MockMvcRequestBuilders.get(PRODUTO_API_URL_PATH + "/" + produtoDtoId)
				                              .contentType(MediaType.APPLICATION_JSON))
		                                      .andExpect(status().isOk())
		                                      .andExpect(jsonPath("$.codigoProduto", Matchers.is(produtoDTO.getCodigoProduto().intValue())))
		                                      .andExpect(jsonPath("$.nomeProduto", Matchers.is(produtoDTO.getNomeProduto())))
		                                      .andExpect(jsonPath("$.descricaoProduto", Matchers.is(produtoDTO.getDescricaoProduto())))
		                                      .andExpect(jsonPath("$.precoProduto", Matchers.is(produtoDTO.getPrecoProduto())));
		                                      
	}
	
	@Test
	void produtoCreateSucess() throws Exception {
		
		ProdutoDTO produtoCreateDto = produtoDTOBuilder.buildProdutoDTO();
		Mockito.when(produtoService.create(produtoCreateDto)).thenReturn(produtoCreateDto);
		mockMvc.perform(MockMvcRequestBuilders.post(PRODUTO_API_URL_PATH)
				                              .contentType(MediaType.APPLICATION_JSON)
				                              .content(JsonConversionUtils.asJsonString(produtoCreateDto)))
		                                      .andExpect(status().isCreated())
		                                      .andExpect(jsonPath("$.codigoProduto", Matchers.is(produtoCreateDto.getCodigoProduto().intValue())))
		                                      .andExpect(jsonPath("$.nomeProduto", Matchers.is(produtoCreateDto.getNomeProduto())))
		                                      .andExpect(jsonPath("$.descricaoProduto", Matchers.is(produtoCreateDto.getDescricaoProduto())))
		                                      .andExpect(jsonPath("$.precoProduto", Matchers.is(produtoCreateDto.getPrecoProduto())));
		                                       
		                                      
		        }
	
	@Test
	void produtoUpdateSucess() throws Exception {
		
		ProdutoDTO produtoUpdateDto = produtoDTOBuilder.buildProdutoDTO();
		Mockito.when(produtoService.update(produtoUpdateDto.getCodigoProduto(), produtoUpdateDto )).thenReturn(produtoUpdateDto);
		mockMvc.perform(MockMvcRequestBuilders.put(PRODUTO_API_URL_PATH + "/" + produtoUpdateDto.getCodigoProduto())
				                              .contentType(MediaType.APPLICATION_JSON)
				                              .content(JsonConversionUtils.asJsonString(produtoUpdateDto)))
		                                      .andExpect(status().isOk())
		                                      .andExpect(jsonPath("$.codigoProduto", Matchers.is(produtoUpdateDto.getCodigoProduto().intValue())))
		                                      .andExpect(jsonPath("$.nomeProduto", Matchers.is(produtoUpdateDto.getNomeProduto())))
		                                      .andExpect(jsonPath("$.descricaoProduto", Matchers.is(produtoUpdateDto.getDescricaoProduto())))
		                                      .andExpect(jsonPath("$.precoProduto", Matchers.is(produtoUpdateDto.getPrecoProduto())));
		 
	}
	
	@Test
	void produtoDeleteteSucess() throws Exception {
		
		ProdutoDTO produtoToDeleteDTO = produtoDTOBuilder.buildProdutoDTO();
		Long produtoIdToDelete = produtoToDeleteDTO.getCodigoProduto();
		doNothing().when(produtoService).delete(produtoIdToDelete);
		mockMvc.perform(delete(PRODUTO_API_URL_PATH + "/" + produtoIdToDelete)
				.contentType(MediaType.APPLICATION_JSON))
		        .andExpect(status().isNoContent());
		
	}
	
	
	
	

}
