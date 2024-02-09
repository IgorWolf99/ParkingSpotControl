package com.api.parkingspotcontrol.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(CpfJaRegistradoException.class)
    private ResponseEntity<String> cpfJaRegistradoHandler(CpfJaRegistradoException ex){
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Cpf já registrado");
    }

    @ExceptionHandler(OcupanteNaoEncontradoException.class)
    private ResponseEntity<String> ocupanteNaoEncontradoHandler(OcupanteNaoEncontradoException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Ocupante não encontrado");
    }

    @ExceptionHandler(PlacaVeiculoJaRegistradaException.class)
    private ResponseEntity<String> placaVeiculoJaRegistradaHandler(PlacaVeiculoJaRegistradaException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Placa já registrada");
    }

    @ExceptionHandler(VagaJaOcupadaException.class)
    private ResponseEntity<String> vagaNaoDisponivelHandler(VagaJaOcupadaException ex){
        return ResponseEntity.status(HttpStatus.CONFLICT).body("Essa vaga já está ocupada");
    }

    @ExceptionHandler(VagaNaoEncontradaException.class)
    private ResponseEntity<String> vagaNaoEncontradaHandler(VagaNaoEncontradaException ex){
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Vaga não encontrada");
    }

}
