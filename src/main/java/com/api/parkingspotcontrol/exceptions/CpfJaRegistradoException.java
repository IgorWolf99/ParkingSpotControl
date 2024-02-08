package com.api.parkingspotcontrol.exceptions;

public class CpfJaRegistradoException extends RuntimeException{
    public CpfJaRegistradoException(String mensagem) {
        super(mensagem);
    }
}
