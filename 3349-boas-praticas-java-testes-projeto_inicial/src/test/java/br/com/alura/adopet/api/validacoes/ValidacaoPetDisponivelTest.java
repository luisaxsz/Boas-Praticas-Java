package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.PetRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.BDDMockito;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class ValidacaoPetDisponivelTest {
    //Dentro dessa classe possui classes com mock
    @InjectMocks
    private ValidacaoPetDisponivel validacao;
    @Mock
    private PetRepository petRepository;

    @Mock
    private Pet pet;

    @Test
    @DisplayName("Deveria permitir adoção do pet")
    void validacaoDisponivel() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(7l, 2l, "qualquer motivo");
        //Transformar em atributo
        //ValidacaoPetDisponivel validacao = new ValidacaoPetDisponivel();

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(false);


        Assertions.assertDoesNotThrow(() -> validacao.validar(dto));
    }

    @Test
    @DisplayName("Não deveria permitir adoção do pet")
    void validacaoIndisponivel() {
        SolicitacaoAdocaoDto dto = new SolicitacaoAdocaoDto(7l, 2l, "qualquer motivo");
        //Transformar em atributo
        //ValidacaoPetDisponivel validacao = new ValidacaoPetDisponivel();

        BDDMockito.given(petRepository.getReferenceById(dto.idPet())).willReturn(pet);
        BDDMockito.given(pet.getAdotado()).willReturn(true);

        //Qual exception foi lançada
        //Validação que quero receber e de onde quero receber
        Assertions.assertThrows(ValidacaoException.class, () -> validacao.validar(dto));
    }

}