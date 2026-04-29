package com.sislocacao.adapter.output.repository.jpa;

import com.sislocacao.adapter.output.repository.jpa.entity.LocacaoEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LocacaoJpaRepository extends JpaRepository<LocacaoEntity, Long > {
    LocacaoEntity findByImovelId(Long id);
    boolean existsByLocadorId(Long locadorId);
}
