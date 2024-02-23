package br.com.alura.adopet.api.service;

import br.com.alura.adopet.api.exception.ValidacaoException;
import br.com.alura.adopet.api.model.Tutor;
import br.com.alura.adopet.api.repository.TutorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class TutorService {

    @Autowired
    private TutorRepository tutorRep;

    public void cadastrar(Tutor tutor) {
        boolean telefoneJaCadastrado = tutorRep.existsByTelefone(tutor.getTelefone());
        boolean emailJaCadastrado = tutorRep.existsByEmail(tutor.getEmail());
        if (telefoneJaCadastrado || emailJaCadastrado) {
            throw new ValidacaoException("Email/Telefone já existente");
        }
        tutorRep.save(tutor);
    }

    public void atualizar(Tutor tutor){
        tutorRep.save(tutor);
    }
}
