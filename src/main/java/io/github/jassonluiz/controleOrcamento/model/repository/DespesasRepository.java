package io.github.jassonluiz.controleOrcamento.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jassonluiz.controleOrcamento.model.entity.Despesas;

public interface DespesasRepository extends JpaRepository<Despesas, Integer>{

}
