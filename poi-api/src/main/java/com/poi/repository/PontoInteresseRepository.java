package com.poi.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.poi.dto.EstatisticaDTO;
import com.poi.entity.PontoInteresse;

public interface PontoInteresseRepository extends JpaRepository<PontoInteresse, Long>{

	@Query(value = "select pos.id as idPosicao, pos.placa, \r\n"
			+ "case when calcular_distancia_ponto(pos.latitude, pos.longitude, :latPOI, :longPOI) <= (:raio/1000) then 'in' else 'out' end as situacao  \r\n"
			+ "from posicao pos \r\n"
			+ "where pos.placa in (select p.placa from posicao p where (:placa is null or p.placa = :placa) and (:dtaPosicao is null or DATE(p.dta_posicao) = STR_TO_DATE(:dtaPosicao, '%Y-%m-%d')) and calcular_distancia_ponto(p.latitude, p.longitude, :latPOI, :longPOI) <= (:raio/1000))\r\n"
			+ "order by pos.placa, pos.dta_posicao", nativeQuery = true)
	List<EstatisticaDTO> findStatisticsByPOIParams(@Param("raio") Integer raio, @Param("latPOI") Double latitudePOI, @Param("longPOI") Double longitudePOI, @Param("placa") String placa, @Param("dtaPosicao") String dtaPosicao);
	
	@Query(value = "select poi.*\r\n"
			+ "from ponto_interesse poi \r\n"
			+ "where exists (select 1 from posicao p where (:placa is null or p.placa = :placa) and (:dtaPosicao is null or DATE(p.dta_posicao) = STR_TO_DATE(:dtaPosicao, '%Y-%m-%d')) and calcular_distancia_ponto(p.latitude, p.longitude, poi.latitude, poi.longitude) <= (poi.raio/1000))\r\n"
			+ "order by poi.nome;", nativeQuery = true)
	List<PontoInteresse> getPOIExistsPosicao(@Param("placa") String placa, @Param("dtaPosicao") String dtaPosicao);
	
}
