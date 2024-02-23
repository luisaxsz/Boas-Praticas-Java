package br.com.alura.adopet.api.validacoes;

import br.com.alura.adopet.api.dto.SolicitacaoAdocaoDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Adocao;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.model.StatusAdocao;
import br.com.alura.adopet.api.repository.AdocaoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class ValidacaoPetAdocaoEmAndamento implements ValidacaoSolicitacaoAdocao {
    @Autowired
    private AdocaoRepository adocaoRep;

    public void validar(SolicitacaoAdocaoDto dto) {
        boolean petTemAdocaoAndamento = adocaoRep.ExistsByPetIdStatus(dto.idPet(), StatusAdocao.AGUARDANDO_AVALIACAO);
        if (petTemAdocaoAndamento) {
            throw new ValidacaoException("Pet já está aguardando avaliação para ser adotado!");
        }
    }
}
