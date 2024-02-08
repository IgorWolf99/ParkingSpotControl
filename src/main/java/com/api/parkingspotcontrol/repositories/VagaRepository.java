package com.api.parkingspotcontrol.repositories;

import com.api.parkingspotcontrol.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {

    @Query(nativeQuery = true, value= "SELECT * FROM vagas ORDER BY vaga;")
    List<Vaga> findAll();
}
