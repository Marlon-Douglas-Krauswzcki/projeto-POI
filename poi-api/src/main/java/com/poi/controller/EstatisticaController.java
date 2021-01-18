package com.poi.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.poi.dto.EstatisticaDTO;
import com.poi.dto.PontoInteresseDTO;
import com.poi.dto.PosicaoPOIDTO;
import com.poi.entity.PontoInteresse;
import com.poi.entity.Posicao;
import com.poi.service.PontoInteresseService;
import com.poi.service.PosicaoService;
import com.poi.service.util.DataUtil;
import com.poi.service.util.JSONUtil;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api-poi/statistic")
public class EstatisticaController {

	@Autowired
	private PontoInteresseService pontoInteresseService;

	@Autowired
	private PosicaoService posicaoService;

	@GetMapping("/get/{id}")
	public PontoInteresseDTO getStatistics(@PathVariable long id) {

		PontoInteresse pontoInteresse = pontoInteresseService.getById(id);

		List<EstatisticaDTO> dtos = pontoInteresseService.getStatiticsByParams(pontoInteresse, null, null);

		PontoInteresseDTO estatisticaPOIs = paraListaEstatisticaPOIDTO(dtos, pontoInteresse);

		return estatisticaPOIs;
	}

	@GetMapping("/getAll")
	public ResponseEntity<List<PontoInteresseDTO>> getAllStatistics() {

		List<PontoInteresse> pontosInteresse = pontoInteresseService.getPOIExistsPosicao(null, null);
		List<PontoInteresseDTO> pontoInteresseDTOs = new ArrayList<PontoInteresseDTO>();

		for (PontoInteresse poi : pontosInteresse) {
			List<EstatisticaDTO> dtos = pontoInteresseService.getStatiticsByParams(poi, null, null);
			PontoInteresseDTO pontoInteresseDTO = paraListaEstatisticaPOIDTO(dtos, poi);
			pontoInteresseDTOs.add(pontoInteresseDTO);
		}

		return ResponseEntity.ok(pontoInteresseDTOs);
	}

	@GetMapping("/getByParams/{params}")
	public ResponseEntity<List<PontoInteresseDTO>> getStatisticsByParams(@PathVariable String params) {

		Map<String, Object> jsonMap;
		try {
			jsonMap = JSONUtil.convertStringToMap(params);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
		}

		String placa = (String) jsonMap.get("placa");
		String strDtaPosicao = (String) jsonMap.get("dtaPosicao");
		
		placa = !"".equals(placa) ? placa : null;
		strDtaPosicao = !"".equals(strDtaPosicao) ? strDtaPosicao : null;

		List<PontoInteresse> pontosInteresse = pontoInteresseService.getPOIExistsPosicao(placa, strDtaPosicao);
		List<PontoInteresseDTO> pontoInteresseDTOs = new ArrayList<PontoInteresseDTO>();

		for (PontoInteresse poi : pontosInteresse) {
			List<EstatisticaDTO> dtos = pontoInteresseService.getStatiticsByParams(poi, placa, strDtaPosicao);
			PontoInteresseDTO pontoInteresseDTO = paraListaEstatisticaPOIDTO(dtos, poi);
			pontoInteresseDTOs.add(pontoInteresseDTO);
		}

		return ResponseEntity.ok(pontoInteresseDTOs);
	}

	public PontoInteresseDTO paraListaEstatisticaPOIDTO(List<EstatisticaDTO> dtos, PontoInteresse pontoInteresse) {

		PontoInteresseDTO pontoInteresseDTO = new PontoInteresseDTO();

		List<PosicaoPOIDTO> posicaoPOIDTOs = new ArrayList<PosicaoPOIDTO>();

		Posicao posicaoInicial = null;
		Posicao posicaoFinal = null;

		for (int i = 0; dtos.size() > i; i++) {

			EstatisticaDTO estatisticaDTO = dtos.get(i);

			if (posicaoInicial == null && "in".equals(estatisticaDTO.getSituacao())) {
				posicaoInicial = posicaoService.getById(estatisticaDTO.getIdPosicao());
			}

			if (posicaoInicial != null && "out".equals(estatisticaDTO.getSituacao())) {
				posicaoFinal = posicaoService.getById(estatisticaDTO.getIdPosicao());
			}
			
			boolean addNaLista = false;
			boolean addSemSaida = false;
			boolean addSemSaidaUltimo = false;

			if(posicaoInicial != null) {
				
				if (posicaoFinal != null && posicaoInicial.getPlaca().equals(estatisticaDTO.getPlaca())) {
					addNaLista = true;
					
				}else if(!posicaoInicial.getPlaca().equals(estatisticaDTO.getPlaca())) {
					addSemSaida = true;
				}
				else if(posicaoFinal == null && (i == dtos.size()-1) ) {
					addSemSaidaUltimo = true;
				}
			}
			
			if(addNaLista || addSemSaida || addSemSaidaUltimo) {
				
				boolean semSaida = addSemSaidaUltimo;
				
				if(addSemSaida) {
					i--;
					posicaoFinal = posicaoService.getById(dtos.get(i).getIdPosicao());
					semSaida = "in".equals(dtos.get(i).getSituacao());
				}
				
				String periodoPOI = DataUtil.intervaloEntreDatas(posicaoInicial.getDtaPosicao(),
						posicaoFinal != null && !semSaida ? posicaoFinal.getDtaPosicao() : null);

				PosicaoPOIDTO posicaoPOIDTO = new PosicaoPOIDTO(posicaoInicial, !semSaida ? posicaoFinal : new Posicao(), periodoPOI != null ? periodoPOI : "Sem data de saída");

				posicaoPOIDTOs.add(posicaoPOIDTO);

				posicaoFinal = null;
				posicaoInicial = null;
			}
		}

		if (!posicaoPOIDTOs.isEmpty()) {
			pontoInteresseDTO = new PontoInteresseDTO();
			pontoInteresseDTO.setPontoInteresse(pontoInteresse);

			pontoInteresseDTO.setPosicaoPOIDTOs(posicaoPOIDTOs);
		}

		return pontoInteresseDTO;
	}

}
