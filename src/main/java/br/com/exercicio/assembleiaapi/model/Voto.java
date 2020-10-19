package br.com.exercicio.assembleiaapi.model;

import java.io.Serializable;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Tolerate;

@Data
@Entity
@Builder
public class Voto implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 3900417864912990983L;
	@Id
	@EqualsAndHashCode.Exclude
	@GeneratedValue(strategy= GenerationType.IDENTITY)
	private Long id;
	private String cpf ;
	@EqualsAndHashCode.Exclude
	private TypeVoto voto;
	 @Tolerate
	 Voto() {}

}
