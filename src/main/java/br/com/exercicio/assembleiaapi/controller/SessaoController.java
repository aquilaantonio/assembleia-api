package br.com.exercicio.assembleiaapi.controller;

import javax.validation.Valid;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.exercicio.assembleiaapi.model.Associado;
import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.Voto;
import br.com.exercicio.assembleiaapi.service.PautaService;
import br.com.exercicio.assembleiaapi.service.SessaoService;

@CrossOrigin("*")
@RestController
@RequestMapping("/assembleia-api/v1/sessoes")
public class SessaoController {
	private static final Logger logger = LoggerFactory.getLogger(SessaoController.class);
	
	@Autowired
	private SessaoService sessaoService;
	
	@Autowired
	private PautaService pautaService;
	
	
	@PostMapping(path ="/{id}")
	public ResponseEntity<Object> votar(@PathVariable("id") long id,@RequestBody @Valid Associado associado ) {
		ResponseEntity<Object> response;
		try {
			if (sessaoService.isAssociadoValid(associado.getCpf()) ) {
				response=	sessaoService.votar(pautaService.findById(Long.valueOf(id)), associado) ? ResponseEntity.ok("Voto computado com sucesso!"): ResponseEntity.ok("Voto não computado") ;
			}else {
				response = ResponseEntity.status(HttpStatus.NOT_FOUND).body("Dados do associado não aceito, por favor revisar as informações");
			}
				
			
		} catch (Exception e) {
				logger.error("JSON fields are not parsable.", e);
				response= ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);
		}
		return response;
		
	}
}
