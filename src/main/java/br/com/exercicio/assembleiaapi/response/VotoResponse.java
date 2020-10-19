/**
 * 
 */
package br.com.exercicio.assembleiaapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.exercicio.assembleiaapi.model.BaseResponse;
import br.com.exercicio.assembleiaapi.model.Voto;
import lombok.Data;

/**
 * @author aquila.pereira
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class VotoResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3153357477715249205L;
	private Voto voto;

}
