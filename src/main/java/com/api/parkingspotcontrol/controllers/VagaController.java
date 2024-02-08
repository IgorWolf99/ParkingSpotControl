package com.api.parkingspotcontrol.controllers;

import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.services.VagaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/parking-spot-control")
@CrossOrigin(origins = "*")
public class VagaController {
    @Autowired
    VagaService vagaService;
    @GetMapping(value = "/vaga")
    public ResponseEntity<List<Vaga>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(vagaService.findAll());
    }
}
