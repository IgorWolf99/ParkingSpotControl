package com.igorwolf.parkingcontrol.entities;

import java.time.LocalDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "tb_parking_spot")
public class ParkingSpot {
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private UUID id;
	
    @Column(name = "numero_vaga", nullable = false, unique = true, length = 10)
    private String parkingSpotNumber;
    
    @Column(name = "placa_carro",nullable = false, unique = true, length = 7)
    private String licensePlateCar;
    
    @Column(name = "modelo_carro",nullable = false, length = 70)
    private String modelCar;
    
    @Column(name = "cor_carro",nullable = false, length = 70)
    private String colorCar;
    
    @Column(name="responsavel",nullable = false, length = 130)
    private String responsibleName;
    
    @Column(name="apartamento",nullable = false, length = 30)
    private String apartment;
    
    @Column(name = "data_registro",nullable = false)
    private LocalDateTime registrationDate;
	 
    
    
    
    public ParkingSpot() {}

	public ParkingSpot(UUID id, String parkingSpotNumber, String licensePlateCar, String modelCar, String colorCar,
			String responsibleName, String apartment, LocalDateTime registrationDate) {
		this.id = id;
		this.parkingSpotNumber = parkingSpotNumber;
		this.licensePlateCar = licensePlateCar;
		this.modelCar = modelCar;
		this.colorCar = colorCar;
		this.responsibleName = responsibleName;
		this.apartment = apartment;
		this.registrationDate = registrationDate;
	}

	public UUID getId() {
		return id;
	}

	public void setId(UUID id) {
		this.id = id;
	}

	public String getParkingSpotNumber() {
		return parkingSpotNumber;
	}

	public void setParkingSpotNumber(String parkingSpotNumber) {
		this.parkingSpotNumber = parkingSpotNumber;
	}

	public String getLicensePlateCar() {
		return licensePlateCar;
	}

	public void setLicensePlateCar(String licensePlateCar) {
		this.licensePlateCar = licensePlateCar;
	}

	public String getModelCar() {
		return modelCar;
	}

	public void setModelCar(String modelCar) {
		this.modelCar = modelCar;
	}

	public String getColorCar() {
		return colorCar;
	}

	public void setColorCar(String colorCar) {
		this.colorCar = colorCar;
	}

	public String getResponsibleName() {
		return responsibleName;
	}

	public void setResponsibleName(String responsibleName) {
		this.responsibleName = responsibleName;
	}

	public String getApartment() {
		return apartment;
	}

	public void setApartment(String apartment) {
		this.apartment = apartment;
	}

	public LocalDateTime getRegistrationDate() {
		return registrationDate;
	}

	public void setRegistrationDate(LocalDateTime registrationDate) {
		this.registrationDate = registrationDate;
	}
    
	
	
	
}
