/**
 * 
 */
package br.com.exercicio.assembleiaapi.request;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.exercicio.assembleiaapi.model.Sessao;
import br.com.exercicio.assembleiaapi.model.TypeVoto;
import br.com.exercicio.assembleiaapi.response.SessaoResponse;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author aquila.pereira
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VotoRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -814066671517078991L;
	
	private String cpf;
	private TypeVoto voto;

}
