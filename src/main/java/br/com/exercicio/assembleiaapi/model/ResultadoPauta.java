package br.com.exercicio.assembleiaapi.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

/**
 * Entidade criada para representa o resultado da votações de uma pauta.
 * @author aquila.pereira
 *
 */
@Data
@Entity
public class ResultadoPauta {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private long totalVotos;
	private long votosSim;
	private long votosNao;

	public ResultadoPauta() {
	}

	public ResultadoPauta(long totalVotos, long votosSim, long votosNao) {
		this.totalVotos = totalVotos;
		this.votosSim = votosSim;
		this.votosNao = votosNao;
	}
}
