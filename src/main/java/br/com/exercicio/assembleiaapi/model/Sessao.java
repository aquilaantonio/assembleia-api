package br.com.exercicio.assembleiaapi.model;

import java.util.Timer;
import java.util.TimerTask;

import javax.persistence.OneToOne;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
/**
 * Classe reperenseta uma sessao de votacao onde seram realizado o controle do tempo a votação de uma pauta
 * @author aquila.pereira
 *
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Sessao {

	@OneToOne
	private Pauta pauta;
	protected boolean ativa = false;
	private long tempoExecucao;
	
	
	@AllArgsConstructor
	class SessaoTask extends TimerTask {
		Timer timer;
		long interval;
		
		@Override
		public void run() {
			  if (interval == 1) {
				  timer.cancel();
				  ativa=false;
				  pauta.setFechado(true);
				  dispararResultado();
			  }
			  --interval;
		}
		
	}
	public void iniciarSessao() {
		int delay = 1000;
		int period = 1000;
		Timer timer = new Timer();
		SessaoTask task = new SessaoTask(timer, this.tempoExecucao);
		timer.schedule(task, delay, period);
		this.ativa=true;
	}
	public void dispararResultado() {
		pauta.contabilizarVotos();
		PautaEventPublisher publisher = new PautaEventPublisher();
		publisher.publisherAssembleaEvent(pauta);
		
	}
}
