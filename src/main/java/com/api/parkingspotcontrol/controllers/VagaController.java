package com.api.parkingspotcontrol.controllers;

import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.services.ControleDeVagasService;
import com.api.parkingspotcontrol.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/parking-spot-control")
@CrossOrigin(origins = "*")
public class VagaController {
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
}
