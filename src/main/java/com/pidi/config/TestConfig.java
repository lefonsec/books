package com.pidi.config;

import java.util.Arrays;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import com.pidi.model.Categoria;
import com.pidi.repositories.CategoriaRepository;

@Configuration
public class TestConfig implements CommandLineRunner{

	@Autowired
	private CategoriaRepository repository; 
	
	@Override
	public void run(String... args) throws Exception {
		
		Categoria cat1 = new Categoria(null,"Leonardo","sobre algo");
		Categoria cat2 = new Categoria(null,"Terror","The Walking Dead");
		
		repository.saveAll(Arrays.asList(cat1,cat2));
	}

}
