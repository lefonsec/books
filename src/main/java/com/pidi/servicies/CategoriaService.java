package com.pidi.servicies;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.pidi.model.Categoria;
import com.pidi.repositories.CategoriaRepository;
import com.pidi.servicies.exception.DataBaseException;
import com.pidi.servicies.exception.ResourceNotFoundException;

@Service
public class CategoriaService {
	
	@Autowired
	private CategoriaRepository catRepository;
	
	public List<Categoria> findAll(){
		return catRepository.findAll();
	}
	public Categoria findById(Long id) {
		Optional<Categoria> obj = catRepository.findById(id);
		return obj.orElseThrow(()-> new ResourceNotFoundException(id));
	}
	public Categoria create(Categoria obj) {
		return catRepository.save(obj);
	}
	public void delete(Long id) {
		try {
			catRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e ) {
			throw new ResourceNotFoundException(id);
		}catch(DataIntegrityViolationException e ) {
			throw new DataBaseException(e.getMessage());
		}
		
	}
	public Categoria update(long id, Categoria obj) {
		Categoria entity = catRepository.getOne(id);
		update(entity,obj);
		return catRepository.save(entity);
	}
	private void update(Categoria entity, Categoria obj) {
		entity.setNome(obj.getNome());
		entity.setDescricao(obj.getDescricao());
		
	}
	
	
	
}
