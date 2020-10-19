package br.com.exercicio.assembleiaapi.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.exercicio.assembleiaapi.model.TypeVoto;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessaoRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8271498200847642249L;
	private long idPauta;
	private int tempoDeSessao;
}
