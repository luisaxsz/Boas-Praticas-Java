package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.ProbabilidadeAdocao;
import br.com.alura.adopet.api.model.TipoPet;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class CalculadoraProbabilidadeAdocaoTest {
    @Test
    @DisplayName("Teste probabilidade alta com idade e peso baixo")
    void testeProbabilidadeAlta() {
        //idade 4 anos e 4kg
        //Arrange
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "91 99999999", "abrigoFeliz@teste.com"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siamês", 4, "Cinza", 4.0f), abrigo);
        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        //act
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        //JUnit
        //Resposta esperada e De onde esperamos essa resposta
        //Probabilidade retornada da calculadora criada
        //Assert
        Assertions.assertEquals(ProbabilidadeAdocao.ALTA, probabilidadeAdocao);
    }
    @Test
    @DisplayName("Teste probabilidade media com idade alta e peso baixo")
    void testeProbabilidadeMedia(){
        //15 anos e 4kg
        Abrigo abrigo = new Abrigo(new CadastroAbrigoDto("Abrigo feliz", "91 99999999", "abrigoFeliz@teste.com"));
        Pet pet = new Pet(new CadastroPetDto(TipoPet.GATO, "Miau", "Siamês", 15, "Cinza", 4.0f), abrigo);

        CalculadoraProbabilidadeAdocao calculadora = new CalculadoraProbabilidadeAdocao();
        ProbabilidadeAdocao probabilidadeAdocao = calculadora.calcular(pet);

        //JUnit
        //Resposta esperada e De onde esperamos essa resposta
        //Probabilidade retornada da calculadora criada
        Assertions.assertEquals(ProbabilidadeAdocao.MEDIA, probabilidadeAdocao);
    }
}