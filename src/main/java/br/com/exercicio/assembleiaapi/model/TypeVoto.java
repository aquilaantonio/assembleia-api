package br.com.exercicio.assembleiaapi.model;

import com.fasterxml.jackson.annotation.JsonValue;

public enum TypeVoto {
	SIM("sim"),
	NAO("nao");
	
	private final String value;
	
	private TypeVoto(final String value) {
		this.value = value;
	}
	
	@JsonValue()
	public String getValue() {
		return value;
	}
}
