/**
 * 
 */
package br.com.exercicio.assembleiaapi.request;

import java.io.Serializable;
import java.util.LinkedHashSet;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

/**
 * @author aquila.pereira
 *
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class PautaRequest implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2954863490723404603L;
	
	private String titulo;
	private LinkedHashSet<String>topicos;

}
