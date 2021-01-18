package com.poi.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "POSICAO")
public class Posicao implements Serializable{

	private static final long serialVersionUID = 1L;

	public Posicao() {
	}
	
	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_POS")
	@GeneratedValue(generator = "SEQ_POS", strategy = GenerationType.AUTO)
	private long id;
	
	@Column(name = "PLACA", length = 8, nullable = false)
	private String placa;
	
	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "DTA_POSICAO", nullable = false)
	private Date dtaPosicao;
	
	@Column(name = "VELOCIDADE", nullable = false)
	private short velicidade;
	
	@Column(name = "LONGITUDE", nullable = false)
	private double longitude;
	
	@Column(name = "LATITUDE", nullable = false)
	private double latitude;
	
	@Column(name = "IGNICAO", nullable = false)
	private boolean ignicao;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getPlaca() {
		return placa;
	}

	public void setPlaca(String placa) {
		this.placa = placa;
	}

	public Date getDtaPosicao() {
		return dtaPosicao;
	}

	public void setDtaPosicao(Date dtaPosicao) {
		this.dtaPosicao = dtaPosicao;
	}

	public short getVelicidade() {
		return velicidade;
	}

	public void setVelicidade(short velicidade) {
		this.velicidade = velicidade;
	}

	public double getLongitude() {
		return longitude;
	}

	public void setLongitude(double longitude) {
		this.longitude = longitude;
	}

	public double getLatitude() {
		return latitude;
	}

	public void setLatitude(double latitude) {
		this.latitude = latitude;
	}

	public boolean isIgnicao() {
		return ignicao;
	}

	public void setIgnicao(boolean ignicao) {
		this.ignicao = ignicao;
	}
}
