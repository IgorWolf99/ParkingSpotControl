package com.api.parkingspotcontrol.exceptions;

public class VagaNaoEncontradaException extends RuntimeException{
    public VagaNaoEncontradaException(String mensagem){
        super(mensagem);
    }
}
