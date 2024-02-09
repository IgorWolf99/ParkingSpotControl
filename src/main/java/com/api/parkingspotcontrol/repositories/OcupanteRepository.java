package com.api.parkingspotcontrol.repositories;

import com.api.parkingspotcontrol.entities.Ocupante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OcupanteRepository extends JpaRepository<Ocupante, UUID> {
    boolean existsByCpf(String cpf);
    boolean existsByPlacaVeiculo(String placaVeiculo);

    boolean existsByCpfAndIdNot(String cpf, UUID id);
    boolean existsByPlacaVeiculoAndIdNot(String placaVeiculo, UUID id);

}
