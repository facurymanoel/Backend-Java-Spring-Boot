package com.everymind.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.everymind.model.Produto;

@Repository
public interface ProdutoRepository  extends JpaRepository<Produto, Long> {
	
	  @Query("select p from Produto p where p.nomeProduto like %?1%")
	  List<Produto> findUserByNome(String nome);

}
