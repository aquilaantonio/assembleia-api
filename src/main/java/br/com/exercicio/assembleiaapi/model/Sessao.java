package br.com.exercicio.assembleiaapi.model;

import java.util.HashSet;

import javax.persistence.Entity;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class Sessao {

	private Pauta pauta;
	private HashSet<Associado> associados;
	
}
