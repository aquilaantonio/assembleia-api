package br.com.exercicio.assembleiaapi.controller;

import java.util.List;

import javax.validation.Valid;

import org.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.Voto;
import br.com.exercicio.assembleiaapi.repository.Pautas;
import br.com.exercicio.assembleiaapi.request.PautaRequest;
import br.com.exercicio.assembleiaapi.request.ResultadoPautaRequest;
import br.com.exercicio.assembleiaapi.request.SessaoRequest;
import br.com.exercicio.assembleiaapi.request.VotoRequest;
import br.com.exercicio.assembleiaapi.response.PautaResponse;
import br.com.exercicio.assembleiaapi.response.ResultadoPautaResponse;
import br.com.exercicio.assembleiaapi.response.SessaoResponse;
import br.com.exercicio.assembleiaapi.response.VotoResponse;
import br.com.exercicio.assembleiaapi.service.PautaService;
import br.com.exercicio.assembleiaapi.service.SessaoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/assembleia-api/v1/pautas")
public class PautaController {
	private static final Logger logger = LoggerFactory.getLogger(PautaController.class);

	@Autowired
	private PautaService pautaService;

	@Autowired
	private SessaoService sessaoService;

	@GetMapping
	public List<Pauta> listarPautas() {
		return pautaService.listar();
	}

	@PostMapping()
	public ResponseEntity<Object> criarPauta(@RequestBody @Valid PautaRequest request) {
		ResponseEntity<Object> response;
		PautaResponse pautaResponse;
		try {
			pautaResponse = pautaService.adicionar(request);
			if (pautaResponse.getPauta() != null) {
				response = ResponseEntity.ok(pautaResponse);
			} else {
				response = ResponseEntity.badRequest().body(pautaResponse);
			}
		} catch (Exception e) {
			logger.error("JSON fields are not parsable.", e);
			response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(e.getMessage());
		}
		return response;

	}

	@PutMapping(path = "/votar")
	public ResponseEntity<Object> votar(@RequestBody @Valid VotoRequest request) {
		ResponseEntity<Object> response;
		VotoResponse votoResponse;
		try {
			if (sessaoService.isAssociadoValid(request.getCpf())) {
				votoResponse = sessaoService.votar(request);
				response = votoResponse.getVoto() != null ? ResponseEntity.ok(votoResponse)	: ResponseEntity.badRequest().body(votoResponse);
			} else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND)
						.body("Dados do associado não aceito, por favor revisar as informações");
			}

		} catch (Exception e) {
			logger.error("", e);
			response = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		return response;

	}

	@PostMapping(path = "/abrirSessao")
	public ResponseEntity<Object> abrirSessao(@RequestBody @Valid SessaoRequest request) {
		SessaoResponse response = sessaoService.abrirSessao(request);
		if (response != null && response.getSessao() != null)
			return ResponseEntity.ok().body(response);

		return ResponseEntity.badRequest().body(response);

	}

	@PostMapping(path = "/resultado")
	public ResponseEntity<Object> resultado(@RequestBody @Valid ResultadoPautaRequest request) {

		ResultadoPautaResponse response = pautaService.obterResultadoPauta(request);
		if (response.getResultado() != null)
			return ResponseEntity.ok().body(response);

		return ResponseEntity.badRequest().body(response);

	}

}
