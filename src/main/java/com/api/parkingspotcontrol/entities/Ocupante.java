package com.api.parkingspotcontrol.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import java.io.Serial;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "ocupantes")
public class Ocupante implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private String nome;

    @Column(nullable = false)
    private String telefone;

    @Column(nullable = false, unique = true)
    private String cpf;

    @Column(nullable = false)
    private String modeloVeiculo;

    @Column(nullable = false, unique = true)
    @ColumnTransformer(write = "UPPER(?)")
    private String placaVeiculo;

    @Column(nullable = false)
    private String corVeiculo;

    @Column(nullable = false)
    private LocalDateTime registroVaga;

    public Ocupante(){}

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getTelefone() {
        return telefone;
    }

    public void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getModeloVeiculo() {
        return modeloVeiculo;
    }

    public void setModeloVeiculo(String modeloVeiculo) {
        this.modeloVeiculo = modeloVeiculo;
    }

    public String getPlacaVeiculo() {
        return placaVeiculo;
    }

    public void setPlacaVeiculo(String placaVeiculo) {
        this.placaVeiculo = placaVeiculo;
    }

    public String getCorVeiculo() {
        return corVeiculo;
    }

    public void setCorVeiculo(String corVeiculo) {
        this.corVeiculo = corVeiculo;
    }

    public LocalDateTime getRegistroVaga() {
        return registroVaga;
    }

    public void setRegistroVaga(LocalDateTime registroVaga) {
        this.registroVaga = registroVaga;
    }

    @Override
    public String toString() {
        return "Ocupante{" +
                "id=" + id +
                ", nome='" + nome + '\'' +
                ", telefone='" + telefone + '\'' +
                ", cpf='" + cpf + '\'' +
                ", modeloVeiculo='" + modeloVeiculo + '\'' +
                ", placaVeiculo='" + placaVeiculo +
                '}';
    }
}
