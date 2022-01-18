package io.github.jassonluiz.controleOrcamento.model.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.jassonluiz.controleOrcamento.model.entity.Receitas;

public interface ReceitasRepository extends JpaRepository<Receitas, Integer>{

}
