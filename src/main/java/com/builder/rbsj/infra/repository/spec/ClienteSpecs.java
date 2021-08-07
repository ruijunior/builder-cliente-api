package com.builder.rbsj.infra.repository.spec;

import java.util.ArrayList;

import javax.persistence.criteria.Predicate;

import org.springframework.data.jpa.domain.Specification;

import com.builder.rbsj.domain.filter.ClienteFilter;
import com.builder.rbsj.domain.model.Cliente;

public class ClienteSpecs {
	
	public static Specification<Cliente> filtrar(ClienteFilter filter) {
		return (root, query, builder) -> {
			var predicates = new ArrayList<Predicate>();
			
			
			
			return builder.and(predicates.toArray(new Predicate[0]));
		};
	}

}
