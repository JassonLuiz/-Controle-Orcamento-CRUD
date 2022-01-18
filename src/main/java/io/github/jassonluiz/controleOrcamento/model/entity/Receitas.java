package io.github.jassonluiz.controleOrcamento.model.entity;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Receitas {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	@NotEmpty(message = "O campo não pode estar vazio!")
	private String descricao;
	
	@NotNull(message = "O campo não pode estar vazio!")
	private Double valor;
	
	@NotNull(message = "O campo não pode estar vazio!")
	@JsonFormat(pattern = "dd/MM/yyyy")
	private LocalDate data;
}
