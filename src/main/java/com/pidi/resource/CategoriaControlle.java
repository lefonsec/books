package com.pidi.resource;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.pidi.model.Categoria;
import com.pidi.servicies.CategoriaService;

@RestController
@RequestMapping(value = "/categorias")
public class CategoriaControlle {

	@Autowired
	private CategoriaService catService;
	
	@GetMapping
	public ResponseEntity<List<Categoria>> findAll(){
		List<Categoria> lista = catService.findAll();
		return ResponseEntity.ok().body(lista);
	}
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Categoria>findById(@PathVariable Long id){
		Categoria obj = catService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Categoria> insert(@Valid @RequestBody Categoria obj){
		obj = catService.create(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).body(obj);
	}
	
	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> delete(@PathVariable Long id){
		catService.delete(id);
		return ResponseEntity.noContent().build();
	}
	@PutMapping(value = "/{id}")
	public ResponseEntity<Categoria> Atualizar(@PathVariable Long id,  @Valid @RequestBody Categoria obj){
		obj = catService.update(id,obj);
		return ResponseEntity.ok().body(obj);
	}
	
	
	
}
