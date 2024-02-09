package com.api.parkingspotcontrol.services;

import com.api.parkingspotcontrol.dtos.OcupanteVagaDTO;
import com.api.parkingspotcontrol.entities.Ocupante;
import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.exceptions.*;
import com.api.parkingspotcontrol.repositories.OcupanteRepository;
import com.api.parkingspotcontrol.repositories.VagaRepository;
import jakarta.transaction.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import javax.naming.ldap.Control;
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
    @Autowired
    ControleDeVagasService controleDeVagasService;

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

    public Object registrarOcupanteVaga(OcupanteVagaDTO ocupanteVagaDTO)  {
        Optional<Vaga> vagaOptional = vagaRepository.findByVaga(ocupanteVagaDTO.vaga().toUpperCase());
        if (vagaOptional.isEmpty()) {
            logger.error("Método > findByVaga() in registrarOcupanteVaga() | Essa vaga não Existe - Vaga: {} ", ocupanteVagaDTO.vaga().toUpperCase());
            throw  new VagaNaoEncontradaException("Essa Vaga não existe");
        }
        var vaga = vagaOptional.get();
        var ocupante = new Ocupante(ocupanteVagaDTO);

        //Verificando vaga disponivel
        if (vaga.isDisponivel()) {
            controleDeVagasService.atribuirVagaOcupante(vaga, ocupante);

        } else {
            logger.error("Método > registrarOcupanteVaga() | Vaga não Disponivel. Vaga: {}", vaga.getVaga().toUpperCase());
            throw new VagaJaOcupadaException("Vaga não disponível");
        }

        // Verificando dados unicos
        if (ocupanteRepository.existsByCpf(ocupanteVagaDTO.cpf())) {
            logger.error("Método > existsByCpf() in registrarOcupanteVaga() | CPF já registrado. - CPF: {}", ocupanteVagaDTO.cpf());
            throw new CpfJaRegistradoException("Cpf já Registrado");
        }
        if (ocupanteRepository.existsByPlacaVeiculo(ocupanteVagaDTO.placaVeiculo().toUpperCase())) {
            logger.error("Método > existsByPlacaVeiculo() in registrarOcupanteVaga() | Placa do Veiculo já registrada. - Placa: {}", ocupanteVagaDTO.placaVeiculo().toUpperCase());
            throw new PlacaVeiculoJaRegistradaException("Placa já Registrada");
        }

        logger.info("Método > registrarOcupanteVaga() | Registro feito. Vaga: {} ", vaga);
        return vagaRepository.save(vaga);
    }

    @Transactional
    public Vaga saveVaga(Vaga vaga) {
        Optional<Vaga> vagaOptional = vagaRepository.findByVaga(vaga.getVaga());

        if(vagaOptional.isPresent()){
            logger.error("Método > findByVaga() in novaVaga() | Vaga já registrada - Vaga: {} " , vaga.getVaga().toUpperCase());
            throw new VagaJaOcupadaException("Vaga já registrada");
        }
        var newVaga = new Vaga(vaga.getVaga());
        logger.info("Método > novaVaga() in novaVaga() | Vaga registrada. Vaga: {}", newVaga.getVaga().toUpperCase());
        return vagaRepository.save(vaga);
    }

    @Transactional
    public void deleteVaga(UUID id) {
        Optional<Vaga> vagaOptional = vagaRepository.findById(id);
        if(vagaOptional.isEmpty()){
            logger.error("Método > findVagaById() | Vaga não encontrada - ID: {} " , id);
            throw new VagaNaoEncontradaException("Vaga não encontrada");
        }

        var vaga = vagaOptional.get();
        if(vaga.getOcupante() != null){
            logger.error("Método >  deleteVaga() | Não é possivel deletar uma vaga em uso - ID ocupante: {} " , vaga.getOcupante().getId());
            throw new VagaJaOcupadaException("Não é possivel deletar uma vaga em uso");
        }

        vagaRepository.delete(vaga);
        logger.info("Método >  deleteVaga() | Vaga deletada - Vaga: {}", vaga);
    }

    @Transactional
    public void deleteOcupante(UUID id)  {
        Optional<Ocupante> ocupanteOptional = ocupanteRepository.findById(id);
        if (ocupanteOptional.isEmpty()) {
            logger.error("Método > findById() in deleteOcupante() | Ocupante não encontrado - ID: {} ", id);
            throw new OcupanteNaoEncontradoException("Ocupante não encontrado");
        }
        Optional<Vaga> vagaOptional = vagaRepository.findVagaByOcupante(id);

        var vaga = vagaOptional.get();
        var ocupante = ocupanteOptional.get();

        controleDeVagasService.desatribuirVagaOcupante(vaga, ocupante);

        ocupanteRepository.delete(ocupante);
        logger.info("Método > deleteOcupante() | Registro deletado com sucesso. Vaga: {} - Ocupante: {}", vaga.getVaga(), ocupante);
    }

}