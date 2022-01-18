package io.github.jassonluiz.controleOrcamento.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import io.github.jassonluiz.controleOrcamento.model.entity.Receitas;
import io.github.jassonluiz.controleOrcamento.model.repository.ReceitasRepository;

@RestController
@RequestMapping("/receitas")
public class ReceitasController {

	public final ReceitasRepository repository;
	
	@Autowired
	public ReceitasController(ReceitasRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Receitas save(@RequestBody Receitas receitas) {
		return repository.save(receitas);
	}
}
