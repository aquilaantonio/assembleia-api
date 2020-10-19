package br.com.exercicio.assembleiaapi.model;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.stereotype.Component;

@Component
public class PautaEventPublisher implements ApplicationEventPublisherAware {
	@Autowired
	private ApplicationEventPublisher applicationEventPublisher;

	public void publisherAssembleaEvent(final Pauta pauta) {
		PautaEvent event = new PautaEvent(this, pauta);
		applicationEventPublisher.publishEvent(event);
	}

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.applicationEventPublisher = applicationEventPublisher;

	}

}
