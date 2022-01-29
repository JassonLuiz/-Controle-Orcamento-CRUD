package io.github.jassonluiz.controleOrcamento.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.jassonluiz.controleOrcamento.model.entity.Despesas;
import io.github.jassonluiz.controleOrcamento.model.repository.DespesasRepository;

@RestController
@RequestMapping("/despesas")
public class DespesasController {

	public final DespesasRepository repository;
	
	@Autowired
	public DespesasController(DespesasRepository repository) {
		this.repository = repository;
	}
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public Despesas save(@RequestBody Despesas despesa) {
		return repository.save(despesa);
	}
	
	@GetMapping
	public List<Despesas> AllDespesas(){
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<?> FindById(@PathVariable Integer id){
		return repository.findById(id)
							.map(record -> ResponseEntity.ok().body(record))
							.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Despesas despesaAtualizada){
		return repository.findById(id)
							.map(despesa ->{
								despesa.setDescricao(despesaAtualizada.getDescricao());
								despesa.setValor(despesaAtualizada.getValor());
								despesa.setData(despesaAtualizada.getData());
								Despesas update = repository.save(despesa);
								return ResponseEntity.ok().body(update);
							}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value = "{id}")
	public void delete(@PathVariable Integer id) {
		repository.findById(id)
					.map(despesa -> {
						repository.delete(despesa);
						return Void.TYPE;
					})
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Despesa não encontrada!"));
	}
	
		
}
