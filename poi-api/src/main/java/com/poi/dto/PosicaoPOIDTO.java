package com.poi.dto;

import com.poi.entity.Posicao;

public class PosicaoPOIDTO {

	private Posicao posicaoInicial;
	private Posicao posicaoFinal;
	private String periodoPOI;
	
	public PosicaoPOIDTO() {
		// TODO Auto-generated constructor stub
	}
	
	public PosicaoPOIDTO(Posicao posicaoInicial, Posicao posicaoFinal, String periodoPOI) {
		this.posicaoInicial = posicaoInicial;
		this.posicaoFinal = posicaoFinal;
		this.periodoPOI = periodoPOI;
	}
	
	public Posicao getPosicaoInicial() {
		return posicaoInicial;
	}

	public void setPosicaoInicial(Posicao posicaoInicial) {
		this.posicaoInicial = posicaoInicial;
	}

	public Posicao getPosicaoFinal() {
		return posicaoFinal;
	}

	public void setPosicaoFinal(Posicao posicaoFinal) {
		this.posicaoFinal = posicaoFinal;
	}

	public String getPeriodoPOI() {
		return periodoPOI;
	}

	public void setPeriodoPOI(String periodoPOI) {
		this.periodoPOI = periodoPOI;
	}
}
