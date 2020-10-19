package br.com.exercicio.assembleiaapi.model;

import java.io.Serializable;

import lombok.Data;
@Data
public class BaseResponse implements Serializable {

	
	/**
	 * 
	 */
	private static final long serialVersionUID = -7549138759079069223L;
	private String faultString;
	
}
