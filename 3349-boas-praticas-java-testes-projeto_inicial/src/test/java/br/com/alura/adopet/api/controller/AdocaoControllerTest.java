package br.com.alura.adopet.api.controller;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.service.AdocaoService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJson;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class AdocaoControllerTest {
    @Autowired
    private MockMvc mvc;
    @MockBean
    private AdocaoService adocaoService;

    @Autowired
    private SolicitacaoAdocaoDto dto;
    @Autowired
    private JacksonTester<SolicitacaoAdocaoDto> jsonDto;

    @Test
    void requisicaoSolicitacaoDeveriaResponderCod400() throws Exception {
        //ARRANGE
        String json = "{}";
        //ACT
        var response = mvc.perform(
                post("/adocoes").content(json).contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        //ASSERT
        Assertions.assertEquals(400, response.getStatus());

    }

    @Test
    void requisicaoSolicitacaoDeveriaResponderCod200() throws Exception {
        //ARRANGE
        //String json = """
                //{
                    //"idPet": 1,
                   // "idTutor": 1,
                   // "motivo": "Motivo qualquer"
              //  }
              //  """;
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(1l, 1l, "Motivo qualquer");
        //ACT
        var response = mvc.perform(
                post("/adocoes").content(jsonDto.write(dto).getJson()).contentType(MediaType.APPLICATION_JSON)
        ).andReturn().getResponse();
        //ASSERT
        Assertions.assertEquals(200, response.getStatus());
        Assertions.assertEquals("Adoção solicitada com sucesso!", response.getContentAsString());
    }

}