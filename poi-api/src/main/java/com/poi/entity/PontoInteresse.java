package com.poi.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "PONTO_INTERESSE")
public class PontoInteresse implements Serializable {

	private static final long serialVersionUID = 1L;

	public PontoInteresse() {

	}

	@Id
	@Column(name = "ID")
	@SequenceGenerator(name = "SEQ_POI")
	@GeneratedValue(generator = "SEQ_POI", strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "NOME", length = 50, nullable = false)
	private String nome;

	@Column(name = "RAIO", nullable = false)
	private int raio;

	@Column(name = "LONGITUDE", nullable = false)
	private double longitude;

	@Column(name = "LATITUDE", nullable = false)
	private double latitude;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public int getRaio() {
		return raio;
	}

	public void setRaio(int raio) {
		this.raio = raio;
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
}