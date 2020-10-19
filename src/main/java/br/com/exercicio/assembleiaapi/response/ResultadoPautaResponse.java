package br.com.exercicio.assembleiaapi.response;


import java.util.List;

import br.com.exercicio.assembleiaapi.model.BaseResponse;
import br.com.exercicio.assembleiaapi.model.ResultadoPauta;
import lombok.Data;

@Data
public class ResultadoPautaResponse extends BaseResponse {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1117656350579905038L;

	
	private  List<ResultadoPauta> resultado;
}
