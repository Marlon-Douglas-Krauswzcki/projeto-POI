package com.poi.dto;

import java.util.List;

import com.poi.entity.PontoInteresse;

public class PontoInteresseDTO {

	public PontoInteresseDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PontoInteresseDTO(PontoInteresse pontoInteresse, List<PosicaoPOIDTO> posicaoPOIDTOs) {
		this.pontoInteresse = pontoInteresse;
		this.posicaoPOIDTOs = posicaoPOIDTOs;
	}
	
	private PontoInteresse pontoInteresse;
	private List<PosicaoPOIDTO> posicaoPOIDTOs;
	

	public PontoInteresse getPontoInteresse() {
		return pontoInteresse;
	}
	
	public void setPontoInteresse(PontoInteresse pontoInteresse) {
		this.pontoInteresse = pontoInteresse;
	}

	public List<PosicaoPOIDTO> getPosicaoPOIDTOs() {
		return posicaoPOIDTOs;
	}

	public void setPosicaoPOIDTOs(List<PosicaoPOIDTO> posicaoPOIDTOs) {
		this.posicaoPOIDTOs = posicaoPOIDTOs;
	}
}
