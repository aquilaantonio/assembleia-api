package br.com.exercicio.assembleiaapi.response;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.exercicio.assembleiaapi.model.Sessao;
import lombok.AllArgsConstructor;
import lombok.Data;
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class SessaoResponse implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4328585402707778360L;
	
	private Sessao sessao;
	
	private String faultString;

}
