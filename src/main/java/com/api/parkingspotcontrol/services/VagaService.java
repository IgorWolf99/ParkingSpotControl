package com.api.parkingspotcontrol.services;

import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.repositories.OcupanteRepository;
import com.api.parkingspotcontrol.repositories.VagaRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VagaService {

    private static final Logger logger = LoggerFactory.getLogger(VagaService.class);
    @Autowired
    VagaRepository vagaRepository;
    @Autowired
    OcupanteRepository ocupanteRepository;

    public List<Vaga> findAll() {
        logger.info("Método > findAll() | Pesquisa todas as vagas - Vagas encontradas: " + vagaRepository.findAll().size());
        return vagaRepository.findAll();
    }

}