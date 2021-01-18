package com.poi.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poi.entity.PontoInteresse;
import com.poi.service.PontoInteresseService;

@RestController
@RequestMapping("/api-poi/poi")
public class PontoInteresseController {

	@Autowired
	private PontoInteresseService service;
	
	@GetMapping("/get/{id}")
	public PontoInteresse get(@PathVariable long id) {
		return service.getById(id);
	}
	
	@GetMapping("/getAll")
	public List<PontoInteresse> getAll() {
		return service.getAll();
	}
	
	@PostMapping("/add")
	public PontoInteresse create(@RequestBody PontoInteresse pontoInteresse) {
		return service.save(pontoInteresse);
	}
	
	@PostMapping("/addAll")
	public List<PontoInteresse> createAll(@RequestBody List<PontoInteresse> pontosInteresse) {
		return service.saveAll(pontosInteresse);
	}
	
	@PutMapping("/update")
	public PontoInteresse update(@RequestBody PontoInteresse pontoInteresse) {
		return service.update(pontoInteresse);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		return service.delete(id);
	}
}
