package com.api.parkingspotcontrol.services;

import com.api.parkingspotcontrol.entities.Ocupante;
import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.exceptions.VagaNaoEncontradaException;
import com.api.parkingspotcontrol.repositories.OcupanteRepository;
import com.api.parkingspotcontrol.repositories.VagaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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

    public Optional<Vaga> findVagaById(@PathVariable UUID id)  {
        Optional<Vaga> vaga = vagaRepository.findById(id);
        if(vaga.isEmpty()){
            logger.error("Método > findVagaById() | Vaga não encontrada - ID: {} " , id);
            throw new VagaNaoEncontradaException("Vaga não encontrada");
        }
        logger.info("Método > findById() | Vaga encontrada - Vaga: {} ", vaga);
        return vaga;
    }

    public Optional<Vaga> findByVaga (String vagaString)  {
        Optional<Vaga> vaga = vagaRepository.findByVaga(vagaString.toUpperCase());
        if(vaga.isEmpty()){
            logger.error("Método > findByVaga() | Vaga não encontrada - Vaga: {} " , vagaString);
            throw  new VagaNaoEncontradaException("Vaga não encontrada");
        }
        logger.info("Método > findByVaga() | Vaga encontrada - Vaga: {} ", vaga);
        return vaga;
    }

    public Optional<Vaga> findVagaByOcupante(UUID ocupanteId)  {
        Optional<Vaga> vagaOptional = vagaRepository.findVagaByOcupante(ocupanteId);
        if (vagaOptional.isEmpty()) {
            logger.error("Método > findById() in vagaPorOcupante() | Vaga não encontrada - ID: {} ", ocupanteId);
            throw new VagaNaoEncontradaException("Vaga não encontrada");
        }
        logger.info("Método > findVagaByOcupante() | Vaga encontrada: {}", vagaOptional);
        return vagaOptional;
    }

    public Object vagasDisponiveis() {
        List<Vaga> vagas = vagaRepository.findAll();
        List<Vaga> disponiveis = new ArrayList<>();
        for (Vaga vaga : vagas){
            if(vaga.isDisponivel()){
                disponiveis.add(vaga);
            }
        }
        if(disponiveis.isEmpty()){
            logger.error("Método > vagasDisponiveis() | Nenhuma vaga disponivel.");
            throw new VagaNaoEncontradaException("Nenhuma vaga disponivel");
        }
        logger.info("Método > vagasDisponiveis() | Vagas Disponiveis: " + disponiveis.size());
        return disponiveis;
    }

    public Optional<Ocupante> findOcupanteById(UUID id){

        return ocupanteRepository.findById(id);
    }

}