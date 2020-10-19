package br.com.exercicio.assembleiaapi.request;

import java.io.Serializable;

import lombok.Data;

@Data
public class ResultadoPautaRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4950678064821443289L;
	
	private Long idPauta;

}
