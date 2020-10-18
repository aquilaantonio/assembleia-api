package br.com.exercicio.assembleiaapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import br.com.exercicio.assembleiaapi.model.Associado;
import br.com.exercicio.assembleiaapi.repository.Associados;

@CrossOrigin("*")
@Service
public class AssociadoService {
	@Autowired
	private Associados associados;
	
	
	public Associado adicionar(Associado associado) {
		return associados.saveAndFlush(associado);
	}

	public Associado findById(String cpf) {
		return associados.findById(cpf).orElse(null);
	}
}
