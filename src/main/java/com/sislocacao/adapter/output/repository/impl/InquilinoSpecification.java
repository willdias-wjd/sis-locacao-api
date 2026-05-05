package com.sislocacao.adapter.output.repository.impl;

import com.sislocacao.adapter.output.repository.jpa.entity.InquilinoEntity;
import jakarta.persistence.criteria.Predicate;
import org.springframework.data.jpa.domain.Specification;

import java.util.ArrayList;
import java.util.List;

public class InquilinoSpecification {

    private InquilinoSpecification() {}

    public static Specification<InquilinoEntity> filtrar(String nome, String cpf, Boolean status) {
        return (root, query, cb) -> {
            List<Predicate> predicates = new ArrayList<>();

            if (nome != null && !nome.isBlank()) {
                String pattern = "%" + nome.toLowerCase() + "%";
                predicates.add(cb.or(
                        cb.like(cb.lower(root.get("nome")), pattern),
                        cb.like(cb.lower(root.get("sobrenome")), pattern)
                ));
            }

            if (cpf != null && !cpf.isBlank()) {
                predicates.add(cb.like(root.get("cpf"), "%" + cpf + "%"));
            }

            if (status != null) {
                predicates.add(cb.equal(root.get("status"), status));
            }

            return cb.and(predicates.toArray(new Predicate[0]));
        };
    }
}
