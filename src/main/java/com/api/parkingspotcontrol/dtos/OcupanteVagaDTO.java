package com.api.parkingspotcontrol.dtos;

import jakarta.validation.constraints.NotBlank;

public record OcupanteVagaDTO(@NotBlank String vaga, @NotBlank String nome, @NotBlank String cpf, @NotBlank String telefone,
                              @NotBlank String modeloVeiculo, @NotBlank String placaVeiculo, @NotBlank String corVeiculo) {
}
