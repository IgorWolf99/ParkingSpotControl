package com.api.parkingspotcontrol;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ParkingSpotControlApplication {

	public static void main(String[] args) {
		SpringApplication.run(ParkingSpotControlApplication.class, args);
		System.out.println("\n\n"+ "-".repeat(65) + " > API EM EXECUÇÃO < " + "-".repeat(65) + "\n\n");
	}

}
