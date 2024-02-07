package br.com.alura.domain;

public class Pet {

    public Pet(String tipo, String nome, String raca, Integer idade, String cor, float peso) {
        this.tipo = tipo;
        this.nome = nome;
        this.raca = raca;
        this.idade = idade;
        this.cor = cor;
        this.peso = peso;
    }

    private String tipo;
    private String nome;
    private String raca;
    private Integer idade;
    private String cor;
    private float peso;
}
