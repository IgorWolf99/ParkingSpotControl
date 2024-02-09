package com.api.parkingspotcontrol.controllers;

import com.api.parkingspotcontrol.dtos.OcupanteVagaDTO;
import com.api.parkingspotcontrol.dtos.VagaDTO;
import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.exceptions.VagaJaOcupadaException;
import com.api.parkingspotcontrol.services.ControleDeVagasService;
import com.api.parkingspotcontrol.services.VagaService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@RestController
@RequestMapping("/parking-spot-control")
@CrossOrigin(origins = "*")
public class VagaController {
    private static final Logger logger = LoggerFactory.getLogger(VagaService.class);
    @Autowired
    VagaService vagaService;
    @Autowired
    ControleDeVagasService controleDeVagasService;
    @GetMapping(value = "/vaga")
    public ResponseEntity<List<Vaga>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vagaService.findAll());
    }

    // Para iniciar a aplicação com dados de vagas já inclusos
    @PostMapping(value = "/vaga/iniciar-dados")
    public ResponseEntity<String> INICIAR_DADOS(){
        try{
            controleDeVagasService.INICIAR_DADOS();
            return ResponseEntity.status(HttpStatus.OK).body("DADOS ADICIONADOS");
        }catch (Exception ex){
            return ResponseEntity.status(HttpStatus.OK).body("ERRO >> DADOS JA EXISTENTES.");
        }
    }

    @GetMapping(value = "/vaga/id/{id}")
    public ResponseEntity<Object> vagaById(@PathVariable UUID id){
        var vaga = vagaService.findVagaById(id);
        return ResponseEntity.status(HttpStatus.OK).body(vaga);
    }


    @GetMapping(value = "/vaga/{vagaString}")
    public ResponseEntity<Object> findByVaga(@PathVariable String vagaString){
        Optional<Vaga> vaga = vagaService.findByVaga(vagaString);
        return ResponseEntity.status(HttpStatus.OK).body(vaga);
    }

    @GetMapping(value = "/vaga/ocupante/{ocupanteId}")
    public ResponseEntity<Object> vagaPorOcupante(@PathVariable UUID ocupanteId) {
        Optional<Vaga> vagaOptional = vagaService.findVagaByOcupante(ocupanteId);
        return  ResponseEntity.status(HttpStatus.OK).body(vagaOptional);
    }

    @GetMapping(value = {"/vaga/disponiveis"})
    public ResponseEntity<Object> vagasDisponiveis(){
        return ResponseEntity.status(HttpStatus.OK).body(vagaService.vagasDisponiveis());
    }

    @PostMapping(value = "/ocupante")
    public ResponseEntity<Object> registrarOcupanteVaga(@RequestBody @Valid OcupanteVagaDTO ocupanteVagaDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaService.registrarOcupanteVaga(ocupanteVagaDTO));
    }

    @PostMapping(value = "/vaga/nova-vaga")
    public ResponseEntity<Object> novaVaga(@Valid @RequestBody VagaDTO nomeVaga) {
        var vaga = new Vaga(nomeVaga.vaga().toUpperCase());
        return ResponseEntity.status(HttpStatus.CREATED).body(vagaService.saveVaga(vaga));
    }

    @DeleteMapping(value = "/vaga/{id}")
    public ResponseEntity<Object> deleteVaga(@PathVariable UUID id) {
        try {
            vagaService.deleteVaga(id);
            return ResponseEntity.status(HttpStatus.OK).body("Vaga deletada");

        }catch (VagaJaOcupadaException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body(ex.getMessage());
        }
    }

    @DeleteMapping(value = "/ocupante/{id}")
    public ResponseEntity<Object> deleteOcupante(@PathVariable UUID id){
        vagaService.deleteOcupante(id);
        return ResponseEntity.status(HttpStatus.OK).body("Registro deletado");
    }

    @PutMapping(value = "/ocupante/{id}")
    public ResponseEntity<Object> updateOcupante(@RequestBody @Valid OcupanteVagaDTO ocupanteVagaDTO, @PathVariable UUID id){
        return  ResponseEntity.status(HttpStatus.OK).body(vagaService.atualizarRegistro(id,ocupanteVagaDTO));
    }

}
