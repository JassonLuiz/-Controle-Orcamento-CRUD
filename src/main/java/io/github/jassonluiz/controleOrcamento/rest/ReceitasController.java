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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	
	@GetMapping
	public List<Receitas> allReceitas(){
		return repository.findAll();
	}
	
	@GetMapping(value = "{id}")
	public ResponseEntity<?> findById(@PathVariable Integer id){
		return repository.findById(id)
							.map(record -> ResponseEntity.ok().body(record))
							.orElse(ResponseEntity.notFound().build());
	}
	
	@PutMapping(value = "{id}")
	public ResponseEntity<?> update(@PathVariable Integer id, @RequestBody Receitas receitaAtualizada){
		return repository.findById(id)
					.map(receita -> {
						receita.setDescricao(receitaAtualizada.getDescricao());
						receita.setValor(receitaAtualizada.getValor());
						receita.setData(receitaAtualizada.getData());
						Receitas update = repository.save(receita);
						return ResponseEntity.ok().body(update);
					}).orElse(ResponseEntity.notFound().build());
	}
	
	@DeleteMapping(value = "{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void delete(@PathVariable Integer id){
		repository.findById(id)
					.map(receita -> {
						repository.delete(receita);
						return Void.TYPE;
					})
					.orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Receita não encontrada!"));
	}
}





