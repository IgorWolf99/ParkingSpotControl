package com.api.parkingspotcontrol.exceptions;

public class PlacaVeiculoJaRegistradaException extends RuntimeException{

    public PlacaVeiculoJaRegistradaException(String mensagem) {
        super(mensagem);
    }
}
