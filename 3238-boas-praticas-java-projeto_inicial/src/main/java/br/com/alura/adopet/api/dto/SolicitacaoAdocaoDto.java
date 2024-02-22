package br.com.alura.adopet.api.dto;

import jakarta.validation.constraints.NotNull;

public record SolicitacaoAdocaoDto(@NotNull Long idPet, @NotNull  Long idTutor, String motivo) {

}
