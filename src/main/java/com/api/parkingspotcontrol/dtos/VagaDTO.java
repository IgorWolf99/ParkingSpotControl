package com.api.parkingspotcontrol.dtos;

import jakarta.validation.constraints.NotBlank;

public record VagaDTO(@NotBlank String vaga) {
}
