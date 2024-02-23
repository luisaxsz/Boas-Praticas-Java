package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Abrigo;
import br.com.alura.adopet.api.model.Pet;
import br.com.alura.adopet.api.repository.AbrigoRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AbrigoService {

    @Autowired
    private AbrigoRepository abrigoRep;

    public void listar() {
        List<Abrigo> abrigos = abrigoRep.findAll();
    }

    public List<Pet> listarPorNomeOuId(String idOuNome) {
        try {
            Long id = Long.parseLong(idOuNome);
            List<Pet> pets = abrigoRep.getReferenceById(id).getPets();
            return pets;
        } catch (EntityNotFoundException enfe) {
            throw new EntityNotFoundException("Pet não encontrado");
        } catch (NumberFormatException e) {
            try {
                List<Pet> pets = abrigoRep.findByNome(idOuNome).getPets();
                throw new NumberFormatException();
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("Pet não encontrado");
            }
        }
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


    public void cadastrarPet(String idOuNome, Pet pet){
        try {
            Long id = Long.parseLong(idOuNome);
            Abrigo abrigo = abrigoRep.getReferenceById(id);
            pet.setAbrigo(abrigo);
            pet.setAdotado(false);
            abrigo.getPets().add(pet);
            abrigoRep.save(abrigo);
        } catch (EntityNotFoundException enfe) {
            throw new EntityNotFoundException("Pet não encontrado");
        } catch (NumberFormatException nfe) {
            try {
                Abrigo abrigo = abrigoRep.findByNome(idOuNome);
                pet.setAbrigo(abrigo);
                pet.setAdotado(false);
                abrigo.getPets().add(pet);
                abrigoRep.save(abrigo);
            } catch (EntityNotFoundException enfe) {
                throw new EntityNotFoundException("Abrigo não encontrado");
            }
        }
    }
}
