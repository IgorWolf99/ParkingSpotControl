package com.api.parkingspotcontrol.entities;

import jakarta.persistence.*;
import org.hibernate.annotations.ColumnTransformer;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

@Entity
@Table(name = "vagas")
public class Vaga implements Serializable {
    @Serial
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;

    @Column(nullable = false)
    private boolean disponivel;

    @Column(nullable = false,unique = true)
    @ColumnTransformer(write = "UPPER(?)")
    private String vaga;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "ocupante_id", nullable = true)
    private Ocupante ocupante;

    public Vaga(){}

    public Vaga(String vaga) {
        this.disponivel = true;
        this.vaga = vaga;
        this.ocupante = null;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public boolean isDisponivel() {
        return disponivel;
    }

    public void setDisponivel(boolean disponivel) {
        this.disponivel = disponivel;
    }

    public String getVaga() {
        return vaga;
    }

    public Ocupante getOcupante() {
        return ocupante;
    }

    public void setVaga(String vaga) {
        this.vaga = vaga;
    }

    public void setOcupante(Ocupante ocupante) {
        this.ocupante = ocupante;
    }

    @Override
    public String toString() {
        return "Vaga{" +
                "id=" + id +
                ", disponivel=" + disponivel +
                ", vaga='" + vaga + '\'' +
                ", ocupante=" + ocupante +
                '}';
    }
}
