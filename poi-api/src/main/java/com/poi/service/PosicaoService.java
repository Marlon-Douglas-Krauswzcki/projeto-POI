package com.poi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poi.entity.Posicao;
import com.poi.repository.PosicaoRepository;

@Service
public class PosicaoService {
	
	@Autowired
	private PosicaoRepository repository;
	
	public Posicao save(Posicao posicao) {
		return repository.save(posicao);
	}
	
	public List<Posicao> saveAll(List<Posicao> posicoes) {
		return repository.saveAll(posicoes);
	}
	
	public List<Posicao> getAll(){
		return repository.findAll();
	}

	public Posicao getById(long id){
		return repository.findById(id).orElse(null);
	}
	
	public String delete(long id) {
		repository.deleteById(id);
		return "Posição com id " + id + " removida com sucesso!";
	}
	
	public Posicao update(Posicao posicao) {
		return repository.save(posicao);
	}

}
