/**
 * 
 */
package br.com.exercicio.assembleiaapi.response;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import br.com.exercicio.assembleiaapi.model.BaseResponse;
import br.com.exercicio.assembleiaapi.model.Pauta;
import lombok.Data;

/**
 * @author aquila.pereira
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PautaResponse extends BaseResponse  {

	/**
	 * 
	 */
	private static final long serialVersionUID = -9196871139807120131L;
	private Pauta pauta;
	

}
