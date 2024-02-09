package br.com.alura;

import br.com.alura.client.ClientHttpConfiguration;
import br.com.alura.service.AbrigoService;
import br.com.alura.service.PetService;

import java.util.Scanner;

public class AdopetConsoleApplication {
    public static void main(String[] args) {
        //Impede de instânciar mais de uma vez
        CommandExecute command = new CommandExecute();
        System.out.println("##### BOAS VINDAS AO SISTEMA ADOPET CONSOLE #####");

        try {
            int opcaoEscolhida = 0;
            while (opcaoEscolhida != 5) {
                exibirMenu();

                String textoDigitado = new Scanner(System.in).nextLine();
                opcaoEscolhida = Integer.parseInt(textoDigitado);

                switch (opcaoEscolhida) {
                    case 1 -> command.executeCommand(new ListarAbrigoCommand());
                    case 2 -> command.executeCommand(new CadastrarAbrigoCommand());
                    case 3 -> command.executeCommand(new ListarPetsCommand());
                    case 4 -> command.executeCommand(new ImportarPetsCommand());
                    case 5 -> System.exit(0);
                    default -> opcaoEscolhida = 0;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static void exibirMenu() {
        System.out.println("1 -> Listar abrigos cadastrados");
        System.out.println("2 -> Cadastrar novo abrigo");
        System.out.println("3 -> Listar pets do abrigo");
        System.out.println("4 -> Importar pets do abrigo");
        System.out.println("5 -> Sair");
    }
}
