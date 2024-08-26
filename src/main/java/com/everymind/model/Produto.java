package com.everymind.model;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import org.hibernate.annotations.GeneratorType;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Produto implements Serializable {
	
    private static final long serialVersionUID = 1L;
	
    @EqualsAndHashCode.Include
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long codigoProduto;
    
    @NotBlank
    @Size(max = 60)
	private String nomeProduto;
    
    @NotBlank
	@Size(max = 255)
	private String descricaoProduto;
    
	private BigDecimal precoProduto;

}
