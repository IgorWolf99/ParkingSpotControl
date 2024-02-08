package com.api.parkingspotcontrol.exceptions;

public class VagaJaOcupadaException extends RuntimeException{
    public VagaJaOcupadaException(String mensagem){
        super(mensagem);
    }
}
