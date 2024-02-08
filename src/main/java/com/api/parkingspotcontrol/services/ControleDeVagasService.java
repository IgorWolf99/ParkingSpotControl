package com.api.parkingspotcontrol.services;

import com.api.parkingspotcontrol.entities.Vaga;
import com.api.parkingspotcontrol.repositories.VagaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

@Service
public class ControleDeVagasService {
    @Autowired
    VagaRepository vagaRepository;

    public void INICIAR_DADOS() {
        String caminho = "add_dados.txt";
        try (BufferedReader br = new BufferedReader(new FileReader(caminho))) {
            String linha;
            ArrayList<Vaga> vagas = new ArrayList<>();
            while ((linha = br.readLine()) != null) {
                vagas.add(new Vaga(linha));
            }

            vagaRepository.saveAll(vagas);
            for (Vaga vaga : vagas) {
                System.out.println(vaga + "\n");
            }

            System.out.println("DADOS ADICIONADOS");
        } catch (IOException e) {
            System.out.println("ERRO no INICIAR_DADOS()" + e.getMessage());
        }
    }
}
