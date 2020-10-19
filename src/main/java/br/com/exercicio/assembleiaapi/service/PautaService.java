package br.com.exercicio.assembleiaapi.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.exercicio.assembleiaapi.controller.PautaController;
import br.com.exercicio.assembleiaapi.model.PautaEvent;
import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.ResultadoPauta;
import br.com.exercicio.assembleiaapi.model.TypeVoto;
import br.com.exercicio.assembleiaapi.model.Voto;
import br.com.exercicio.assembleiaapi.repository.Pautas;
import br.com.exercicio.assembleiaapi.request.PautaRequest;
import br.com.exercicio.assembleiaapi.request.ResultadoPautaRequest;
import br.com.exercicio.assembleiaapi.response.PautaResponse;
import br.com.exercicio.assembleiaapi.response.ResultadoPautaResponse;

@CrossOrigin("*")
@Service
public class PautaService implements ApplicationListener<PautaEvent> {
	private static final Logger logger = LoggerFactory.getLogger(PautaService.class);
	@Autowired
	private Pautas pautas;

	public List<Pauta> listar() {
		return pautas.findAll();
	}

	public PautaResponse adicionar(PautaRequest request) {
		PautaResponse response = new PautaResponse();
		try {
			Pauta pauta = convertRequest(request);
			response.setPauta(pautas.saveAndFlush(pauta));
		} catch (Exception e) {
			logger.error("Erro ao criar a pauta",e);
			response.setFaultString("Falha ao criar pauta");
		}
		return response;
	}

	public Pauta findById(Long id) {
		return pautas.findById(id).orElse(null);
	}

	public Pauta update(Pauta pautaUpdate) {
		pautaUpdate.setDataAtualizacao(LocalDateTime.now());
		return pautas.saveAndFlush(pautaUpdate);
	}

	private Pauta convertRequest(PautaRequest request) {
		return Pauta.builder().titulo(request.getTitulo()).topicos(request.getTopicos())
				.dataCriacao(LocalDateTime.now()).build();
	}
	
	public ResultadoPautaResponse obterResultadoPauta(ResultadoPautaRequest request) {
		ResultadoPautaResponse response =  new ResultadoPautaResponse();
		List<Pauta> list = new ArrayList<Pauta>();
		try {
			
			if (request.getIdPauta()!=null && request.getIdPauta()>0) {
				list.add(this.findById(request.getIdPauta()));
			}else {
				list.addAll(this.listar());
			}
			response.setResultado(this.obterListaResultadoPauta(list));
		} catch (Exception e) {
			logger.error("",e);
			response.setFaultString("Erro ao obter o resultado da pauta");
		}
		return response;
		
	}
	
	private List<ResultadoPauta> obterListaResultadoPauta(List<Pauta> listPauta) {
		List<ResultadoPauta> listResultPauta = new ArrayList<ResultadoPauta>();
		listPauta.forEach(l -> {
				listResultPauta.add(l.getResultado());
		}

		);
		return listResultPauta;
	}

	@Override
	public void onApplicationEvent(PautaEvent event) {
		pautas.saveAndFlush(event.getPauta());
		
	}
}
