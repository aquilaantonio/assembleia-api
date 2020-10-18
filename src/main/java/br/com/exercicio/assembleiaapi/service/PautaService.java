package br.com.exercicio.assembleiaapi.service;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.Voto;
import br.com.exercicio.assembleiaapi.repository.Pautas;

@CrossOrigin("*")
@Service
public class PautaService {
	@Autowired
	private Pautas pautas;

	public List<Pauta> listar() {
		return pautas.findAll();
	}
	public Pauta adicionar(Pauta pauta) {
		pauta.setDataCriacao(LocalDateTime.now());
		return pautas.saveAndFlush(pauta);
	}

	public Pauta findById(Long id) {
		return pautas.findById(id).orElse(null);
	}

	public Pauta update(Pauta pauta, Pauta pautaUpdate) {
		//setPautaValues(pautaUpdate, pauta);
		return pautas.saveAndFlush(pautaUpdate);
	}

	public boolean isJSONValid(String object) {
		try {
			return new ObjectMapper().readTree(object) != null;
		} catch (IOException e) {
			return false;
		}
	}

	private void setPautaValues(Pauta jsonPauta, Pauta pauta) {

//		pauta.setTitulo((String) jsonPauta.get("titulo"));
//		LinkedHashSet<String> topicos = new LinkedHashSet<String>();
//		jsonPauta.getJSONArray("topicos").forEach(p -> topicos.add((String) p));
//		pauta.setTopicos(topicos);
//		HashSet<Voto> votos = new HashSet<Voto>();
//		jsonPauta.getJSONArray("votos").forEach(p -> votos.add((Voto) p));
//		pauta.setVotos(votos);
//		pauta.setDataAtualizacao(LocalDateTime.now());
	}

}
