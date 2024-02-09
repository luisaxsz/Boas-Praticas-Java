package br.com.alura.service;


import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.domain.Abrigo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintStream;
import java.net.http.HttpResponse;

import static net.bytebuddy.matcher.ElementMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

public class AbrigoServiceTest {

    //Simula instância da classe
    private ClientHttpConfiguration client = mock(ClientHttpConfiguration.class);
    private AbrigoService abrigoService = new AbrigoService(client);
   private HttpResponse<String> response = mock(HttpResponse.class);
    //Criação objeto teste
    private Abrigo abrigo = new Abrigo("Teste", "61981880392", "abrigo_alura@gmail.com");

    @Test
    public void deveVerificarQuandoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L);
        //Resultados esperados
        String expectedAbrigosCadastrados = "Abrigos cadastrados:";
        String expectedIdENome = "0 - Teste";
        //Retorno syso do meto listar abrigo em array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        //O que ta escrito no array de bytes
        System.setOut(printStream);
        //Mockar comportamento do responseBody -> criando body response
        when(response.body()).thenReturn("[{"+abrigo.toString()+"}]");
        //Quando client htt chamar metodo retorna resposta mockada
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);
        //Quando chamar retorna o mockado
        abrigoService.listarAbrigo();
        //Linhas que pegam array de strings
        String[] lines = baos.toString().split(System.lineSeparator());
        String actualAbrigosCadastrados = lines[0];
        String actualIdENome = lines[1];
        //Espera que seja igual ao retornado no service
        Assertions.assertEquals(expectedAbrigosCadastrados, actualAbrigosCadastrados);
        Assertions.assertEquals(expectedIdENome, actualIdENome);
    }

    @Test
    public void deveVerificarQuandoNaoHaAbrigo() throws IOException, InterruptedException {
        abrigo.setId(0L);
        //Resultados esperados
        String expected = "Não há abrigos cadastrados";

        //Retorno syso do meto listar abrigo em array de bytes
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        PrintStream printStream = new PrintStream(baos);
        //O que ta escrito no array de bytes
        System.setOut(printStream);
        //Mockar comportamento do responseBody -> criando body response
        when(response.body()).thenReturn("[]");
        when(client.dispararRequisicaoGet(anyString())).thenReturn(response);
        //Quando chamar retorna o mockado
        abrigoService.listarAbrigo();
        String[] lines = baos.toString().split(System.lineSeparator());
        String actual = lines[0];
        //Espera que seja igual ao retornado no service
        Assertions.assertEquals(expected, actual);
    }
}
