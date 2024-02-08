package com.api.parkingspotcontrol.services;

import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.repositories.OcupanteRepository;
import com.api.parkingspotcontrol.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {
    @Autowired
    VagaRepository vagaRepository;
    @Autowired
    OcupanteRepository ocupanteRepository;

    public List<Vaga> findAll() {
        return vagaRepository.findAll();
    }

}