package br.com.exercicio.assembleiaapi.model;

import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;

import org.springframework.lang.NonNullApi;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Builder.Default;
import lombok.Data;
import lombok.NonNull;
import lombok.experimental.Tolerate;

@Data
@Entity
@Builder(toBuilder=true)
public class Pauta {
	@Id
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private LocalDateTime dataCriacao;
	private LocalDateTime dataAtualizacao;
	@NonNull
	private String titulo;
	@NonNull
	private LinkedHashSet<String>topicos;
	@ManyToMany
	@JoinColumn(name = "cpf")
	private Set<Associado> associados = new HashSet<Associado>();
	
	 @Tolerate
     Pauta() {}
}
