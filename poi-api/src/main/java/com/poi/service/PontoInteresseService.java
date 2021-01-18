package com.poi.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.poi.dto.EstatisticaDTO;
import com.poi.entity.PontoInteresse;
import com.poi.repository.PontoInteresseRepository;

@Service
public class PontoInteresseService {
	
	@Autowired
	private PontoInteresseRepository repository;
	
	public PontoInteresse save(PontoInteresse pontoInteresse) {
		return repository.save(pontoInteresse);
	}
	
	public List<PontoInteresse> saveAll(List<PontoInteresse> pontosInteresse) {
		return repository.saveAll(pontosInteresse);
	}
	
	public List<PontoInteresse> getAll(){
		return repository.findAll();
	}

	public PontoInteresse getById(long id){
		return repository.findById(id).orElse(null);
	}
	
	public String delete(long id) {
		repository.deleteById(id);
		return "Ponto de Interesse com id " + id + " removido com sucesso!";
	}
	
	public PontoInteresse update(PontoInteresse pontoInteresse) {
		return repository.save(pontoInteresse);
	}
	
	
	public List<EstatisticaDTO> getStatiticsByParams(PontoInteresse pontoInteresse, String placa, String dtaPosicao) {
		return repository.findStatisticsByPOIParams(pontoInteresse.getRaio(), pontoInteresse.getLatitude(), pontoInteresse.getLongitude(), placa, dtaPosicao);
	}
	
	public List<PontoInteresse> getPOIExistsPosicao(String placa, String dtaPosicao) {
		return repository.getPOIExistsPosicao(placa, dtaPosicao);
	}
	
}
