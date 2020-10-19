package br.com.exercicio.assembleiaapi.model;

import org.springframework.context.ApplicationEvent;

import lombok.Data;
public class PautaEvent extends ApplicationEvent {
	/**
	 * 
	 */
private static final long serialVersionUID = -22347897793055379L;
private Pauta pauta;
	public PautaEvent(Object source, Pauta pauta) {
		super(source);
		this.pauta = pauta;
	}

	public Pauta getPauta() {
		return pauta;
	}

}
