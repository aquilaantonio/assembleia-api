package br.com.exercicio.assembleiaapi.controller;

import static org.junit.Assert.assertNotEquals;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import br.com.exercicio.assembleiaapi.model.Pauta;
import br.com.exercicio.assembleiaapi.model.ResultadoPauta;
import br.com.exercicio.assembleiaapi.model.Sessao;
import br.com.exercicio.assembleiaapi.model.TypeVoto;
import br.com.exercicio.assembleiaapi.model.Voto;
import br.com.exercicio.assembleiaapi.request.PautaRequest;
import br.com.exercicio.assembleiaapi.request.SessaoRequest;
import br.com.exercicio.assembleiaapi.request.VotoRequest;
import br.com.exercicio.assembleiaapi.response.PautaResponse;
import br.com.exercicio.assembleiaapi.response.SessaoResponse;
import br.com.exercicio.assembleiaapi.response.VotoResponse;
import br.com.exercicio.assembleiaapi.service.PautaService;
import br.com.exercicio.assembleiaapi.service.SessaoService;
import junit.framework.TestCase;

@RunWith(MockitoJUnitRunner.class)
public class PautaControllerTest extends TestCase {

    @InjectMocks
    private PautaController pautaController = new PautaController();

    @Mock
    private PautaService pautaService;
    @Mock
    public SessaoService sessaoService;
    
    @Mock
    public PautaRequest pautaRequest;
    @Mock
    public SessaoRequest sessaoRequest;
   
    @Mock
    public VotoRequest votoRequest;

    @Test
    public void deveListarPautasQuandoChamado() {

        List<Pauta> objetorEsperado = new ArrayList<>();

        Mockito.when(pautaService.listar()).thenReturn(objetorEsperado);

        List<Pauta> objetorObtido = pautaController.listarPautas();

        Assert.assertEquals(objetorEsperado, objetorObtido);
    }

    @Test
    public void devCriarPautaQuandoReceberParametroValido() {

        PautaResponse response = new PautaResponse();
        Pauta pauta = Pauta.builder().titulo("").topicos(new LinkedHashSet<String>())
                .dataCriacao(LocalDateTime.now()).build();
        response.setPauta(pauta);

        ResponseEntity<Object> objetorEsperado = ResponseEntity.ok(response);

        Mockito.when(pautaService.adicionar(pautaRequest)).thenReturn(response);

        ResponseEntity<Object> objetorObtido = pautaController.criarPauta(pautaRequest);

        Assert.assertEquals(objetorEsperado, objetorObtido);
    }

    @Test
    public void devRetornarErro404AoCriarPautaQuandoReceberParametroInvalido() {

        PautaResponse response = new PautaResponse();
        ResponseEntity<Object> objetorEsperado = ResponseEntity.badRequest().body(response);

        Mockito.when(pautaService.adicionar(pautaRequest)).thenReturn(response);

        ResponseEntity<Object> objetorObtido = pautaController.criarPauta(pautaRequest);

        Assert.assertEquals(objetorEsperado, objetorObtido);
    }

    @Test
    public void devRetornarErroAoCriarPautaQuandoReceberParametroNulo() {

        ResponseEntity<Object> objetorEsperado = ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(null);

        ResponseEntity<Object> objetorObtido = pautaController.criarPauta(pautaRequest);

        Assert.assertEquals(objetorEsperado, objetorObtido);
    }
    
    
    @Test
    public void devRetornarSessaoQuandoReceberParametrosValidos() {

        SessaoResponse response = new SessaoResponse();
        Sessao sessao = new Sessao();
        response.setSessao(sessao);

        ResponseEntity<Object> objetorEsperado = ResponseEntity.ok(response);

        when(sessaoService.abrirSessao(sessaoRequest)).thenReturn(response);

        ResponseEntity<Object> objetorObtido = pautaController.abrirSessao(sessaoRequest);

        assertEquals(objetorEsperado, objetorObtido);
    }
    
    
    @Test
    public void devRetornarSessaoComTempoDeEmSegudos() {

        SessaoResponse response = new SessaoResponse();
        Sessao sessao = new Sessao(Pauta.builder().titulo("").topicos(new LinkedHashSet<String>()).build(),true,180);
        response.setSessao(sessao);

        ResponseEntity<Object> objetorEsperado = ResponseEntity.ok().body(response);

        when(sessaoService.abrirSessao(sessaoRequest)).thenReturn(response);
        ResponseEntity<Object> objetorObtido = pautaController.abrirSessao(sessaoRequest);

        assertEquals(objetorEsperado, objetorObtido);
    }
    
    
    @Test
    public void devRetornarSessaoComErroDeCriacao() {

        SessaoResponse response = new SessaoResponse();
        Sessao sessao = new Sessao(Pauta.builder().titulo("").topicos(new LinkedHashSet<String>()).build(),true,180);
        response.setSessao(sessao);

        ResponseEntity<Object> objetorEsperado = ResponseEntity.badRequest().build();

        ResponseEntity<Object> objetorObtido = pautaController.abrirSessao(sessaoRequest);

        assertEquals(objetorEsperado, objetorObtido);
    }
    
    @Test
    public void devRetornarErro404AoEnviarCpfInvalido() {

        ResponseEntity<Object> objetorEsperado = ResponseEntity.notFound().build();

        ResponseEntity<Object> objetorObtido = pautaController.votar(votoRequest);

        assertEquals(objetorEsperado.getStatusCode(), objetorObtido.getStatusCode());
    }
    @Test
    public void devRetornarVotoComDadosValidos() {

        VotoResponse response = new VotoResponse();
        Voto voto = Voto.builder().cpf("48155950034").voto(TypeVoto.NAO).build();
        response.setVoto(voto);

        ResponseEntity<Object> objetorEsperado = ResponseEntity.ok().body(response);
        VotoRequest request = new VotoRequest();
        request.setCpf("48155950034");
        request.setVoto(TypeVoto.NAO);
        when(sessaoService.votar(request)).thenReturn(response);
        when(sessaoService.isAssociadoValid("48155950034")).thenReturn(true);
        ResponseEntity<Object> objetorObtido = pautaController.votar(request);

        assertEquals(objetorEsperado, objetorObtido);
    }
    
    @Test
    public void devRetornarTotalVotos8() {

    	Set<Voto> votos= new HashSet<Voto>();
    	votos.add(Voto.builder().cpf("48155950034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("48155950").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("481559500").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("155950034").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("8155950034").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("5950034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("50034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("4815595").voto(TypeVoto.NAO).build());
    	Pauta pauta = Pauta.builder().titulo("Teste resutaldo").topicos(new LinkedHashSet<String>()).votos(votos).fechado(true).build();
    	pauta.contabilizarVotos();
    	ResultadoPauta objetorObtido =pauta.getResultado();
    	ResultadoPauta objetorEsperado = new ResultadoPauta(8, 3, 5);
        assertEquals(objetorEsperado, objetorObtido);
    }
    
    
    
    @Test
    public void naoDevRetornarTotal10Votos() {

    	Set<Voto> votos= new HashSet<Voto>();
    	votos.add(Voto.builder().cpf("48155950034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("48155950").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("48155950034").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("481559500").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("155950034").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("8155950034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("8155950034").voto(TypeVoto.SIM).build());
    	votos.add(Voto.builder().cpf("5950034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("50034").voto(TypeVoto.NAO).build());
    	votos.add(Voto.builder().cpf("4815595").voto(TypeVoto.NAO).build());
    	Pauta pauta = Pauta.builder().titulo("Teste resutaldo").topicos(new LinkedHashSet<String>()).votos(votos).fechado(true).build();
    	pauta.contabilizarVotos();
    	ResultadoPauta objetorObtido =pauta.getResultado();
    	ResultadoPauta objetorEsperado = new ResultadoPauta(10, 4, 6);
        assertNotEquals(objetorEsperado, objetorObtido);
    }
    
}