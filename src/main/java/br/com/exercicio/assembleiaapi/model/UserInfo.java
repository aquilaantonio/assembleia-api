package br.com.exercicio.assembleiaapi.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@JsonIgnoreProperties(ignoreUnknown = true)
@Data
public class UserInfo implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2368146297715527127L;
	public static final String ABLE_TO_VOTE = "ABLE_TO_VOTE";
	public static final String UNABLE_TO_VOTE = "UNABLE_TO_VOTE";
	
	@JsonProperty("status")
	private String status;
}
