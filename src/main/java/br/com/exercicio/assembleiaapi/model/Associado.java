package br.com.exercicio.assembleiaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.experimental.Tolerate;

@Data
@Entity
public class Associado implements Serializable{

	
	/**
	 * 
	 */
	private static final long serialVersionUID = 6407561830778518539L;

	@NonNull
	private String nome;
	
	@Id
	@NonNull
	private String cpf;
	
	private boolean voto;
	
	 @Tolerate
	 Associado() {}
}
