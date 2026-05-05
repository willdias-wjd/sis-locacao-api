package com.sislocacao.adapter.output.repository.jpa;

import com.sislocacao.adapter.output.repository.jpa.entity.InquilinoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

@Repository
public interface InquilinoJpaRepository extends JpaRepository<InquilinoEntity, Long>,
        JpaSpecificationExecutor<InquilinoEntity> {
    boolean existsByCpf(String cpf);
    boolean existsByRg(String rg);
}
