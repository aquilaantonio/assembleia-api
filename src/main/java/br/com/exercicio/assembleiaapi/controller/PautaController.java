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
import br.com.exercicio.assembleiaapi.repository.Pautas;
import br.com.exercicio.assembleiaapi.service.PautaService;

@CrossOrigin("*")
@RestController
@RequestMapping("/assembleia-api/v1/pautas")
public class PautaController {
	private static final Logger logger = LoggerFactory.getLogger(PautaController.class);
	
	@Autowired
	private PautaService service;
	
	@GetMapping
	public List<Pauta> listar() {
		return service.listar();
	}
	
	@PostMapping
	public Pauta adicionar(@RequestBody @Valid Pauta pauta) {

		return service.adicionar(pauta);

	}
	
	@PutMapping
	public ResponseEntity<Pauta> update(@RequestBody @Valid Pauta pauta) {
		try {
			pauta.toBuilder();
			if(pauta.getId()!=null ) {
				Pauta transactionToUpdate = service.findById(Long.valueOf(pauta.getId()));
				if(transactionToUpdate == null){
					logger.info("Pauta não encontrada");
					return ResponseEntity.notFound().build(); 
				}else {
					Pauta pautaUpdate = service.update(transactionToUpdate, pauta);
					return ResponseEntity.ok(pautaUpdate);
				}
			}else {
				return ResponseEntity.badRequest().body(null);
			}
		}catch(Exception e) {
			logger.error("JSON fields are not parsable.", e);
			return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
	}
	
}
