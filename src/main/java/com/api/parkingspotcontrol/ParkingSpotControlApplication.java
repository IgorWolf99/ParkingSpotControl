package com.api.parkingspotcontrol;

import io.swagger.v3.oas.annotations.OpenAPIDefinition;
import io.swagger.v3.oas.annotations.info.Info;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@OpenAPIDefinition(info = @Info(title = "Parking Spot Control", version = "1", description = "API desenvolvida para o gerenciamento de vagas em um estacionamento."))
public class ParkingSpotControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSpotControlApplication.class, args);
		System.out.println("\n\n"+ "-".repeat(65) + " > API EM EXECUÇÃO < " + "-".repeat(65) + "\n\n");
	}

}
