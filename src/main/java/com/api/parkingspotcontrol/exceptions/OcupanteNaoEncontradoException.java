package com.api.parkingspotcontrol.exceptions;

public class OcupanteNaoEncontradoException extends RuntimeException{
    public OcupanteNaoEncontradoException(String mensagem){
        super(mensagem);
    }
}
