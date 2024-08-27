package com.everymind.exception;

import javax.persistence.EntityNotFoundException;

public class ProdutoNotFoundException extends EntityNotFoundException {
	
	public ProdutoNotFoundException(Long id) {
		
		super(String.format("Produto com id %s não existe", id));
		 
	}

}
