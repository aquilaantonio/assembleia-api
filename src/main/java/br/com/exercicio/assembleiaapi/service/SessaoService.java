package br.com.exercicio.assembleiaapi.service;

import java.io.IOException;

import org.apache.http.HttpEntity;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.CrossOrigin;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.Sessao;
import br.com.exercicio.assembleiaapi.model.UserInfo;
import br.com.exercicio.assembleiaapi.model.Voto;
import br.com.exercicio.assembleiaapi.repository.Votos;
import br.com.exercicio.assembleiaapi.request.SessaoRequest;
import br.com.exercicio.assembleiaapi.request.VotoRequest;
import br.com.exercicio.assembleiaapi.response.SessaoResponse;
import br.com.exercicio.assembleiaapi.response.VotoResponse;

@CrossOrigin("*")
@Service
public class SessaoService {
	Logger logger = LoggerFactory.getLogger(SessaoService.class);
	private static final String HTTPS_USER_INFO_HEROKUAPP_COM_USERS = "https://user-info.herokuapp.com/users/";

	@Autowired
	private PautaService pautaSerivce;

	@Autowired
	private Votos votos;
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	@Autowired
	private ObjectMapper mapper;

	private static Sessao sessao = new Sessao();

	public VotoResponse votar(VotoRequest votoRequest) {
		VotoResponse retorno = new VotoResponse();
		try {
			Voto voto = Voto.builder().cpf(votoRequest.getCpf()).voto(votoRequest.getVoto()).build();
			Pauta pauta = sessao.isAtiva() ? sessao.getPauta() : null;
			if (pauta != null) {
				sessao.setPauta(pauta.getVotos().add(votos.saveAndFlush(voto)) ? pautaSerivce.update(pauta) : pauta);
				retorno.setVoto(voto);
			} else {
				retorno.setFaultString("Voto nao computado!");
			}
		} catch (Exception e) {
			logger.error("Erro ao votar", e);
		}
		return retorno;

	}

	public boolean isAssociadoValid(String cpf) {
		boolean retorno = false;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(HTTPS_USER_INFO_HEROKUAPP_COM_USERS.concat(cpf));
		try (CloseableHttpResponse response = httpClient.execute(request)) {
			HttpEntity entity = response.getEntity();

			if (entity != null) {
				UserInfo result = getEntityFromResponse(entity, UserInfo.class);
				logger.info("Retorno servico de validacao -> " + result.getStatus());
				retorno = result.getStatus().equalsIgnoreCase(UserInfo.ABLE_TO_VOTE);
			}

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

		return retorno;
	}

	private <T> T getEntityFromResponse(HttpEntity httpEntity, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {
		String body = EntityUtils.toString(httpEntity, "UTF-8");
		return this.mapper.readValue(body, clazz);
	}

	public SessaoResponse abrirSessao(SessaoRequest request) {
		SessaoResponse retorno = null;
		try {

			retorno = new SessaoResponse();
			long segundos = request.getTempoDeSessao() > 0 ? request.getTempoDeSessao() * 60 : 60;
			Pauta pauta = pautaSerivce.findById(request.getIdPauta());
			if (!pauta.isFechado()) {
				Sessao sessao = new Sessao(pauta, false, segundos);
				sessao.iniciarSessao();
				this.sessao = sessao;
				retorno.setSessao(sessao);
			} else {
				retorno.setFaultString("Sessao nao pode ser iniciada pois a Pauta já foi votada");
			}
		} catch (Exception e) {
			logger.error("Falha ao tentar abrir a sessao", e);
		}
		return retorno;

	}

}
