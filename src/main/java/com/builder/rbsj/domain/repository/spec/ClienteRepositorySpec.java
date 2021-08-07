package com.builder.rbsj.domain.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.builder.rbsj.domain.filter.ClienteFilter;
import com.builder.rbsj.domain.model.Cliente;

public class ClienteRepositorySpec {
	
	public static Specification<Cliente> filtrarPor(ClienteFilter filtro) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();
			
			if(filtro.getNome() != null) {
				predicates.add(builder.like(root.get("nome"), "%"+filtro.getNome()+"%"));
			}
			
			if(filtro.getEmail() != null) {
				predicates.add(builder.equal(root.get("email"), filtro.getEmail()));
			}
			
			if(filtro.getCelular() != null) {
				predicates.add(builder.equal(root.get("celular"), filtro.getCelular()));
			}
			
			if (filtro.getNascimentoInicio() != null) {
				predicates.add(builder.greaterThanOrEqualTo(root.get("nascimento"), 
						filtro.getNascimentoInicio()));
			}
			
			if (filtro.getNascimentoFim() != null) {
				predicates.add(builder.lessThanOrEqualTo(root.get("nascimento"), 
						filtro.getNascimentoFim()));
			}
					
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
