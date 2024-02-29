package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroAbrigoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class AbrigoServiceTest {

    @InjectMocks
    private AbrigoService abrigoService;
    @Mock
    private AbrigoRepository abrigoRepository;

    @Mock
    private PetRepository petRepository;

    @Mock
    private CadastroAbrigoDto dto;

    @Mock
    private Abrigo abrigo;


    @Test
    void listarTodosOsAbrigosExistentes() {
        //ACT
        abrigoService.listar();
        //ASSERT
        then(abrigoRepository).should().findAll();
    }

    @Test
    void deveriaCadastrarOAbrigo() {
        abrigoService.cadatrar(dto);
        then(abrigoRepository).should().save(new Abrigo(dto));
    }

    @Test
    void naoDeveriaPermitirCadastroAbrigo() {
        given(abrigoRepository.existsByNomeOrTelefoneOrEmail(dto.nome(), dto.telefone(), dto.email())).willReturn(true);
        assertThrows(ValidacaoException.class, () -> abrigoService.cadatrar(dto));
    }

    @Test
    void deveriaListaPetsDoAbrigoPeloNome(){
        //ARRANGE
        String nome = "teste";
        given(abrigoRepository.findByNome(nome)).willReturn(Optional.of(abrigo));
        //ACT
        abrigoService.listarPetsDoAbrigo(nome);
        //ASSERT
        then(petRepository).should().findByAbrigo(abrigo);
    }

    @Test
    void carregarAbrigoPeloId(){
        //ARANGE
        Long id = 1l;
        given(abrigoRepository.findById(id)).willReturn(Optional.of(abrigo));
        //ACT
        abrigoService.carregarAbrigo(String.valueOf(id));
        //ASSERT
        then(abrigoRepository).should().findById(id);
    }

    @Test
    void deveriaLancarErroAoBuscarAbrigoPorId(){
        //ARANGE
        Long id = 1l;
        given(abrigoRepository.findById(id)).willReturn(Optional.empty());
        //ACT
        //ASSERT
        assertThrows(ValidacaoException.class, () -> abrigoService.carregarAbrigo(String.valueOf(id)));
    }

    @Test
    void deveriaCarregarAbrigoPeloNome(){
        //ARANGE
        String nome = "teste";
        given(abrigoRepository.findByNome(nome)).willReturn(Optional.of(abrigo));
        //ACT
        abrigoService.carregarAbrigo(nome);
        //ASSERT
        then(abrigoRepository).should().findByNome(nome);
    }

    @Test
    void deveriaLancarErroAoBuscarAbrigoPorNome(){
        //ARANGE
        String nome = "Teste";
        given(abrigoRepository.findByNome(nome)).willReturn(Optional.empty());
        //ACT
        //ASSERT
        assertThrows(ValidacaoException.class, () -> abrigoService.carregarAbrigo(nome));
    }


}