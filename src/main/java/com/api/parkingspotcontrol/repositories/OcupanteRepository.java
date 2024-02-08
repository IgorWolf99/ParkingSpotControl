package com.api.parkingspotcontrol.repositories;

import com.api.parkingspotcontrol.entities.Ocupante;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OcupanteRepository extends JpaRepository<Ocupante, UUID> {
}
