package br.com.exercicio.assembleiaapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class PautaEventPublisher {
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publisherAssembleaEvent(final Pauta pauta) {
		PautaEvent event = new PautaEvent(this, pauta);
		applicationEventPublisher.publishEvent(event);
	}


}
