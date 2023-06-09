package com.igorwolf.parkingcontrol.repositories;

import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.igorwolf.parkingcontrol.entities.ParkingSpot;

@Repository
public interface ParkingSpotRepository extends JpaRepository<ParkingSpot, UUID>{
	
	boolean existsByParkingSpotNumber(String parkingSpotNumber);
	boolean existsByApartment(String apartment);
	boolean existsByLicensePlateCar(String liscencePlateCar);
	
	boolean existsByLicensePlateCarAndIdNot(String licensePlateCar, UUID id);    
	boolean existsByParkingSpotNumberAndIdNot(String parkingSpotNumber, UUID id);    
	boolean existsByApartmentAndIdNot(String apartment, UUID id);
}
