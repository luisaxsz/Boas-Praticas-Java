package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.CadastroPetDto;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.BDDMockito.then;

@ExtendWith(MockitoExtension.class)
class PetServiceTest {
    @InjectMocks
    private PetService petService;

    @Mock
    private PetRepository petRepository;

    @Mock
    private CadastroPetDto dto;

    @Mock
    private Abrigo abrigo;

    @Mock
    private List<Pet> petList;
    @Test
    void deveriaRetornarTodosOsPetsDisponiveis(){
        petService.buscarPetsDisponiveis();
        then(petRepository).should().findAllByAdotadoFalse();
    }

    @Test
    void deveriaCadastrarPet(){
        petService.cadastrarPet(abrigo, dto);
        then(petRepository).should().save(new Pet(dto, abrigo));
    }

}