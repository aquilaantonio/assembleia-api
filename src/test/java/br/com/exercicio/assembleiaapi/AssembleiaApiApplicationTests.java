package br.com.exercicio.assembleiaapi;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AssembleiaApiApplicationTests {

	private static final String URL_PADRAO = "http://localhost:8080/assembleia-api/v1/pautas/";

	@Test
	public void contextLoads() {
	}

	@Test
	public void listarPautas() throws ClientProtocolException, IOException {

		HttpUriRequest request = new HttpGet(URL_PADRAO);
try {
	
	HttpResponse httpResponse = HttpClientBuilder.create().build().execute(request);
	assertThat(httpResponse.getStatusLine().getStatusCode(), equalTo(HttpStatus.SC_NOT_FOUND));
} catch (Exception e) {
e.printStackTrace();
}

	}

}
