package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.io.IOException;

public class ListarPetsCommand implements Command{

    ClientHttpConfiguration client = new ClientHttpConfiguration();
    PetService petService = new PetService(client);
    @Override
    public void execute() {
        try {
            petService.listarPetsAbrigo();
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
