package com.everymind.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.everymind.dto.ProdutoDTO;
import com.everymind.exception.ProdutoNotFoundException;
import com.everymind.mapper.ProdutoMapper;
import com.everymind.model.Produto;
import com.everymind.repository.ProdutoRepository;

import lombok.AllArgsConstructor;

@AllArgsConstructor
@Service
public class ProdutoService {

	private static final ProdutoMapper produtoMapper = ProdutoMapper.INSTANCE;

	private ProdutoRepository produtoRepository;
	
	public List<ProdutoDTO> findAll(){
		
		 return produtoRepository.findAll()
				                 .stream()
				                 .map(produtoMapper::toDTO)
				                 .collect(Collectors.toList());
	}
	
	public ProdutoDTO findById(Long id) {
		
		  return produtoRepository.findById(id)
				                  .map((p) -> produtoMapper.toDTO(p))
				                  .orElseThrow(() -> new ProdutoNotFoundException(id));
	}
	
	@Transactional
	public ProdutoDTO create(ProdutoDTO produtoDTO) {
		
		  Produto produtoToCreate = produtoMapper.toModel(produtoDTO);
		  Produto createProduto = produtoRepository.save(produtoToCreate);
		  return produtoMapper.toDTO(createProduto);
		
	}
	
	@Transactional
	public ProdutoDTO update(Long id, ProdutoDTO produtoDTO) {
		
		  verifyExists(id);
	      Produto produto = new Produto();
	      produto.setCodigoProduto(id);
	      produtoDTO.setCodigoProduto(produto.getCodigoProduto());
	      Produto produtoToCreate = produtoMapper.toModel(produtoDTO);
	      Produto createProduto = produtoRepository.save(produtoToCreate);
	      return  produtoMapper.toDTO(createProduto);
	      
	      
	}
	
	@Transactional
    public void delete(Long id) {
		 
		  verifyExists(id);
		  produtoRepository.deleteById(id);
	 
	}
	
	private void verifyExists(Long id) {
		
		 produtoRepository.findById(id)
		                  .orElseThrow(() -> new ProdutoNotFoundException(id));
		
	}

}
