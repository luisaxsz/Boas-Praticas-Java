package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.dto.PetDto;
import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import br.com.alura.adopet.api.repository.PetRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRep;

    @Autowired
    private PetRepository petRepository;

    public void listar() {
        List<Abrigo> abrigos = abrigoRep.findAll();
    }

    public List<PetDto> listarPorNomeOuId(String idOuNome) {
        Abrigo abrigo = carregarAbrigoString(idOuNome);
        return petRepository.findByAbrigo(String.valueOf(abrigo)).stream().map(PetDto::new).toList();
    }

    public void cadastrar(Abrigo abrigo) {
        boolean nomeJaCadastrado = abrigoRep.existsByNome(abrigo.getNome());
        boolean telefoneJaCadastrado = abrigoRep.existsByTelefone(abrigo.getTelefone());
        boolean emailJaCadastrado = abrigoRep.existsByEmail(abrigo.getEmail());

        if (nomeJaCadastrado || telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Dados já cadastrados para outro abrigo!");
        }
        abrigoRep.save(abrigo);
    }

    public Abrigo carregarAbrigoString(String idOuNome) {
        Optional<Abrigo> optional;
        try {
            Long id = Long.parseLong(idOuNome);
            optional = abrigoRep.findById(id);
        } catch (NumberFormatException e) {
            optional = Optional.ofNullable(abrigoRep.findByNome(idOuNome));
        }

        return optional.orElseThrow(() -> new ValidacaoException("Abrigo não encontrado"));
    }
}
