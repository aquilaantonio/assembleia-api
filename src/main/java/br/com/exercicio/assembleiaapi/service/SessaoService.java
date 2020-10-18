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

import br.com.exercicio.assembleiaapi.model.Associado;
import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.UserInfo;
import br.com.exercicio.assembleiaapi.repository.Associados;
import br.com.exercicio.assembleiaapi.repository.Pautas;

@CrossOrigin("*")
@Service
public class SessaoService {
	Logger logger= LoggerFactory.getLogger(SessaoService.class);
	private static final String HTTPS_USER_INFO_HEROKUAPP_COM_USERS = "https://user-info.herokuapp.com/users/";

	@Autowired
	private Pautas pautas;
	
	@Autowired
	private Associados associados;
	private CloseableHttpClient httpClient = HttpClients.createDefault();
	@Autowired
	private ObjectMapper mapper;
	public boolean votar(Pauta pauta , Associado associado) {
		boolean retorno;
		 associados.findById(associado.getCpf()).orElse(associados.saveAndFlush(associado));
		 retorno = pauta.getAssociados().add(associado)? pautas.saveAndFlush(pauta)!=null : false ;
		 logger.info("Resultado do voto do Associado"+associado.getCpf()+ " = " + retorno);
		return retorno;
		
	}
	
	public boolean isAssociadoValid(String cpf) {
		boolean retorno = false;
		CloseableHttpClient httpClient = HttpClients.createDefault();
		HttpGet request = new HttpGet(HTTPS_USER_INFO_HEROKUAPP_COM_USERS.concat(cpf));
		 try (CloseableHttpResponse response = httpClient.execute(request)) {
	            HttpEntity entity = response.getEntity();

	            if (entity != null) {
	                UserInfo result = getEntityFromResponse(entity,UserInfo.class);
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

}
