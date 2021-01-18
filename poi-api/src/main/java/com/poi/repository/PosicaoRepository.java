package com.poi.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.poi.entity.Posicao;

public interface PosicaoRepository extends JpaRepository<Posicao, Long> {

}
