package com.api.parkingspotcontrol.repositories;

import com.api.parkingspotcontrol.entities.Vaga;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface VagaRepository extends JpaRepository<Vaga, UUID> {

    @Query(nativeQuery = true, value= "SELECT * FROM vagas ORDER BY vaga;")
    List<Vaga> findAll();

    Optional<Vaga> findByVaga(String vaga);

    @Query(nativeQuery = true, value = """
        SELECT v.*
        FROM vagas v
        INNER JOIN ocupantes r ON v.ocupante_id = r.id
        WHERE r.id = :ocupanteId
    """)
    Optional<Vaga> findVagaByOcupante(@Param("ocupanteId") UUID responsavelId);

}
