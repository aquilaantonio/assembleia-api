package br.com.exercicio.assembleiaapi.model;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

import org.springframework.lang.NonNullApi;
import org.w3c.dom.ls.LSInput;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Tolerate;

/**
 * Classe utilizada para representar uma pauta, onde deve conter os votos do
 * associados.
 * 
 * @author aquila.pereira
 *
 */
@Data
@Entity
@Builder(toBuilder = true)
public class Pauta {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	@NonNull
	private String titulo;
	@NonNull
	private LinkedHashSet<String> topicos;
	@OneToMany
	private Set<Voto> votos = new HashSet<Voto>();
	private boolean fechado;
	@OneToOne
	private ResultadoPauta resultado;

	@Tolerate
	Pauta() {
	}

	public void contabilizarVotos() {
		if (fechado) {
			long quantidadeNAO = this.getVotos().stream().filter(p -> p.getVoto().equals(TypeVoto.NAO)).count();
			long quantidadeSim = this.getVotos().stream().filter(p -> p.getVoto().equals(TypeVoto.SIM)).count();
			long quantidadeTotal = quantidadeNAO + quantidadeSim;
			
			this.resultado = new ResultadoPauta(quantidadeTotal, quantidadeSim, quantidadeNAO);
		}
	}
}
