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

import com.poi.entity.Posicao;
import com.poi.service.PosicaoService;

@RestController
@RequestMapping("/api-poi/position")
public class PosicaoController {

	@Autowired
	private PosicaoService service;
	
	@GetMapping("/get/{id}")
	public Posicao getPosicao(@PathVariable long id) {
		return service.getById(id);
	}
	
	@GetMapping("/getAll")
	public List<Posicao> getAllPosicao() {
		return service.getAll();
	}
	
	@PostMapping("/add")
	public Posicao create(@RequestBody Posicao posicao) {
		return service.save(posicao);
	}
	
	@PostMapping("/addAll")
	public List<Posicao> createAll(@RequestBody List<Posicao> posicoes) {
		return service.saveAll(posicoes);
	}
	
	@PutMapping("/update")
	public Posicao update(@RequestBody Posicao posicao) {
		return service.update(posicao);
	}
	
	@DeleteMapping("/delete/{id}")
	public String delete(@PathVariable long id) {
		return service.delete(id);
	}
	
}
